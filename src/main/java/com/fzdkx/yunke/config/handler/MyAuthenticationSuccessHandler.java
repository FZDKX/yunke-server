package com.fzdkx.yunke.config.handler;

import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TUser;
import com.fzdkx.yunke.common.RedisConstant;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.RedisService;
import com.fzdkx.yunke.utils.JSONUtils;
import com.fzdkx.yunke.utils.JWTUtils;
import com.fzdkx.yunke.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.fzdkx.yunke.common.RedisConstant.TOKEN_PREFIX;

/**
 * @author 发着呆看星
 * @create 2024/6/4
 * SpringSecurity 自定义登录成功处理器
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 登录成功
        // 获取 security 中的用户信息
        TUser user = ((LoginUser) authentication.getPrincipal()).getTUser();
        // 数据脱敏
        user.dataDesensitization();
        // 获取user的json格式
        String json = JSONUtils.toJSON(user);
        // 生成token
        String token = JWTUtils.createJWT(json);
        // 设置redis
        boolean isRemember = request.getParameter("remember").equals("true");
        long timeout = RedisConstant.DEFAULT_EXPIRE_TIME;
        // 如果选择记住我，那么过期时间为 7天
        if (isRemember) {
            timeout = RedisConstant.EXPIRE_TIME;
        }
        redisService.setValue(TOKEN_PREFIX + user.getId(), token, timeout, TimeUnit.SECONDS);
        // 创建成功返回格式
        Result<String> result = Result.success(token);
        // 返回给前端
        ResponseUtils.write(response, JSONUtils.toJSON(result));
    }
}
