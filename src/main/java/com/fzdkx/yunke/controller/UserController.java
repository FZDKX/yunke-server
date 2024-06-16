package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TUser;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.UserDetailsVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author 发着呆看星o
 * @create 2024/6/13
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public Result<PageInfo<TUser>> pageList(Integer pageSize, Integer pageNum) {
        return userService.getListByPage(pageSize, pageNum);
    }

    @GetMapping("/details/{id}")
    public Result<UserDetailsVO> getUserDetails(@PathVariable("id") Integer id) {
        return userService.selectUserDetailsById(id);
    }

    @GetMapping("/{id}")
    public Result<TUser> getUser(@PathVariable("id") Integer id) {
        return userService.selectUserById(id);
    }

    @PostMapping
    public Result<String> addUser(@RequestBody TUser tUser, Authentication authentication) {
        return userService.addUser(tUser, authentication);
    }

    @PutMapping
    public Result<String> editUser(@RequestBody TUser tUser, Authentication authentication) {
        return userService.editUser(tUser, authentication);
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUserById(id);
    }
    @DeleteMapping("/batch")
    public Result<String> batchDelete(@RequestBody IdListVO idListVO) {
        return userService.batchDelete(idListVO);
    }

}
