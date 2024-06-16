package com.fzdkx.yunke.config.handler;

import com.fzdkx.yunke.common.CodeEnum;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.utils.JSONUtils;
import com.fzdkx.yunke.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 发着呆看星
 * @create 2024/6/4
 * SpringSecurity 自定义登录失败处理器
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // 登录失败
        Result<Object> result = Result.fail(CodeEnum.LOGIN_FAIL);
        String json = JSONUtils.toJSON(result);
        // 返回给前端
        ResponseUtils.write(response, json);
    }
}
