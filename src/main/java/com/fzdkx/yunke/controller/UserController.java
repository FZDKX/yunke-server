package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TUser;
import com.fzdkx.yunke.bean.vo.EditAddUserVO;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.UserDetailsVO;
import com.fzdkx.yunke.bean.vo.UserVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 发着呆看星o
 * @create 2024/6/13
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserService userService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('user:list')")
    // 用户列表
    public Result<PageInfo<UserVO>> pageList(Integer pageSize, Integer pageNum) {
        return userService.getListByPage(pageSize, pageNum);
    }

    @PreAuthorize("hasAnyAuthority('user:view')")
    @GetMapping("/details/{id}")
    // 查看用户详情
    public Result<UserDetailsVO> getUserDetails(@PathVariable("id") Integer id) {
        return userService.selectUserDetailsById(id);
    }

    @PreAuthorize("hasAnyAuthority('user:edit')")
    @GetMapping("/{id}")
    // 编辑时 查看用户信息
    public Result<UserVO> getUser(@PathVariable("id") Integer id) {
        return userService.selectUserById(id);
    }

    @PreAuthorize("hasAnyAuthority('user:add')")
    @PostMapping
    public Result<String> addUser(@RequestBody EditAddUserVO user, Authentication authentication) {
        // 给密码加密
        if (StringUtils.hasText(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userService.addUser(user, authentication);
    }

    @PreAuthorize("hasAnyAuthority('user:edit')")
    @PutMapping
    public Result<String> editUser(@RequestBody EditAddUserVO user, Authentication authentication) {
        return userService.editUser(user, authentication);
    }

    @PreAuthorize("hasAnyAuthority('user:delete')")
    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUserById(id);
    }

    @PreAuthorize("hasAnyAuthority('user:delete')")
    @DeleteMapping("/batch")
    public Result<String> batchDelete(@RequestBody IdListVO idListVO) {
        return userService.batchDelete(idListVO);
    }

    // 主页面加载时，加载用户权限信息
    @GetMapping("/all")
    public Result<LoginUser> getUserAll(Authentication authentication) {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 消除密码
        loginUser.getTUser().dataDesensitization();
        return Result.success(loginUser);
    }

    // 页面加载时，加载用户按钮信息
    @GetMapping("/perm")
    public Result<List<String>> getUserPerm(Authentication authentication) {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return userService.getUserPermButton(loginUser.getTUser().getId());
    }

    // 加载负责人列表
    @GetMapping("/owner")
    public Result<List<TUser>> getAllOwner() {
        return userService.queryAllOwner();
    }
}
