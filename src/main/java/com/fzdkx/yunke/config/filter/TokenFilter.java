package com.fzdkx.yunke.config.filter;

import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.SystemUser;
import com.fzdkx.yunke.bean.dao.TUser;
import com.fzdkx.yunke.common.CodeEnum;
import com.fzdkx.yunke.common.RedisConstant;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.common.UriConstant;
import com.fzdkx.yunke.service.RedisService;
import com.fzdkx.yunke.service.UserService;
import com.fzdkx.yunke.utils.JSONUtils;
import com.fzdkx.yunke.utils.JWTUtils;
import com.fzdkx.yunke.utils.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author 发着呆看星
 * @create 2024/6/6
 */
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisService redisService;

    @Resource
    private UserService userService;

    //spring boot框架的ioc容器中已经创建好了该线程池，可以注入直接使用
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 1) 如果是登录请求，直接放行
        if (UriConstant.LOGIN_URI.equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        // 2) 如果是其他请求，进行token校验
        String token = request.getHeader("Authorization");
        // 2.1) 未携带token
        if (!StringUtils.hasText(token)) {
            // 创建统一返回对象
            Result<Object> result = Result.fail(CodeEnum.TOKEN_IS_EMPTY);
            // 转JSON
            String json = JSONUtils.toJSON(result);
            // 输出给前端
            ResponseUtils.write(response, json);
            return;
        }
        // 2.2) 携带token，校验 token 是否被篡改
        if (!JWTUtils.verifyJWT(token)) {
            // 创建统一返回对象
            Result<Object> result = Result.fail(CodeEnum.TOKEN_IS_FALSIFY);
            // 转JSON
            String json = JSONUtils.toJSON(result);
            // 输出给前端
            ResponseUtils.write(response, json);
            return;
        }
        // 2.3) token未被篡改，查询redis，判断token是否过期
        // 从token中解析出用户信息
        TUser user = JWTUtils.getUser(token);
        // 通过用户信息从redis中获取token，进行校验
        String redisToken = redisService.get(RedisConstant.TOKEN_PREFIX + user.getId());
        if (!StringUtils.hasText(redisToken)) {
            // 创建统一返回对象
            Result<Object> result = Result.fail(CodeEnum.TOKEN_IS_EXPIRED);
            // 转JSON
            String json = JSONUtils.toJSON(result);
            // 输出给前端
            ResponseUtils.write(response, json);
            return;
        }
        // 携带的token 与 redis中的token不同，那么已在另一设备登录
        if (!redisToken.equals(token)) {
            // 创建统一返回对象
            Result<Object> result = Result.fail(CodeEnum.TWO_CLIENT_LOGIN);
            // 转JSON
            String json = JSONUtils.toJSON(result);
            // 输出给前端
            ResponseUtils.write(response, json);
            return;
        }
        // 校验通过后，将用户信息设置在security的上下文环境中
        // 查询 user detail
        LoginUser loginUser = (LoginUser) userService.loadUserByUsername(user.getUsername());
        // 创建SystemUser
        SystemUser systemUser = new SystemUser(loginUser.getTUser().getId(), loginUser.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(systemUser, loginUser.getPassword(), loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 如果选择 记住密码 ，那么进行token刷新
        threadPoolTaskExecutor.execute(() -> {
            // token续期
            String remember = request.getHeader("remember");
            // 默认一天
            long timeout = RedisConstant.DEFAULT_EXPIRE_TIME;
            // 如果为true，那么续期时长为 7天
            if (Boolean.parseBoolean(remember)) {
                timeout = RedisConstant.EXPIRE_TIME;
            }
            redisService.expire(RedisConstant.TOKEN_PREFIX + user.getId(), timeout, TimeUnit.SECONDS);
        });
        // 放行，继续执行下一个过滤器
        filterChain.doFilter(request, response);
    }

}
