package com.fzdkx.yunke.config;

import com.fzdkx.yunke.config.filter.TokenFilter;
import com.fzdkx.yunke.config.handler.MyAccessDeniedHandler;
import com.fzdkx.yunke.config.handler.MyAuthenticationFailureHandler;
import com.fzdkx.yunke.config.handler.MyAuthenticationSuccessHandler;
import com.fzdkx.yunke.config.handler.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/6
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private TokenFilter tokenFilter;

    // 加密器(必须要)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 解决跨域问题
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //允许任何来源，http://localhost:8081
        configuration.setAllowedOrigins(List.of("*"));
        //允许任何请求方法，post、get、put、delete
        configuration.setAllowedMethods(List.of("*"));
        //允许任何的请求头
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   CorsConfigurationSource corsConfigurationSource) throws Exception {
        return httpSecurity
                .authorizeHttpRequests((http) -> {
                    // 只放开登录请求
                    http.requestMatchers("/api/login").permitAll()
                            // 其他请求需要登录后才能访问
                            .anyRequest().authenticated();
                })
                .formLogin((login) -> {
                    // 设置请求授权
                    login.loginProcessingUrl("/api/login")
                            // 设置用户名参数
                            .usernameParameter("username")
                            // 设置密码参数
                            .passwordParameter("password")
                            // 设置登录成功处理器
                            .successHandler(myAuthenticationSuccessHandler)
                            // 设置登录失败处理器
                            .failureHandler(myAuthenticationFailureHandler);
                })
                // 设置成功退出处理器
                .logout((logout) -> logout.logoutUrl("/api/logout")
                        .logoutSuccessHandler(myLogoutSuccessHandler))
                // 设置权限不足处理器
                .exceptionHandling((exception) -> exception.accessDeniedHandler(myAccessDeniedHandler))
                // 允许请求跨域
                .cors((cors) -> cors.configurationSource(corsConfigurationSource))
                // 添加自定义Filter，在UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 禁用 跨站伪造请求保护
                .csrf(AbstractHttpConfigurer::disable)
                // 禁用session策略
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
