package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 发着呆看星
 * @create 2024/6/12
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @GetMapping("/auto")
    public Result<String> autoLogin() {
        return Result.success();
    }
}
