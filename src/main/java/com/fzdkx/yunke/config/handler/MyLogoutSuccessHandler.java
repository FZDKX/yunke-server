package com.fzdkx.yunke.config.handler;

import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.utils.JSONUtils;
import com.fzdkx.yunke.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 发着呆看星
 * @create 2024/6/4
 * 自定义成功退出登录处理器
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        // 安全退出成功
        Result<Object> result = Result.success();
        String json = JSONUtils.toJSON(result);
        // 返回给前端
        ResponseUtils.write(response, json);
    }
}
