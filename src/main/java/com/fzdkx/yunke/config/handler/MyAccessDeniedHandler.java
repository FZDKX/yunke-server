package com.fzdkx.yunke.config.handler;

import com.fzdkx.yunke.common.CodeEnum;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.utils.JSONUtils;
import com.fzdkx.yunke.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 发着呆看星
 * @create 2024/6/4
 * SpringSecurity 自定义访问异常处理器
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 权限不足
        Result<Object> result = Result.fail(CodeEnum.NO_AUTHORITY);
        String json = JSONUtils.toJSON(result);
        // 返回给前端
        ResponseUtils.write(response, json);
    }
}
