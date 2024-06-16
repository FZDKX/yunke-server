package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.*;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.UserDetailsVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.mapper.TPermissionMapper;
import com.fzdkx.yunke.mapper.TRoleMapper;
import com.fzdkx.yunke.mapper.TUserMapper;
import com.fzdkx.yunke.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/7
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private TUserMapper tUserMapper;
    @Resource
    private TRoleMapper roleMapper;
    @Resource
    private TPermissionMapper tPermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库在查询用户信息
        TUser tUser = tUserMapper.selectByUsername(username);
        // 从数据库中查询用户角色信息
        List<TRole> roleList = roleMapper.selectByUserId(tUser.getId());
        // 从数据库中查询用户 菜单权限信息
        List<TPermission> menuList = tPermissionMapper.selectByUserIdAndType(tUser.getId(), "menu");
        // 从数据库中查询用户 按钮权限信息
        List<TPermission> buttenList = tPermissionMapper.selectByUserIdAndType(tUser.getId(), "button");
        return new LoginUser(tUser, roleList, menuList, buttenList);
    }

    @Override
    public Result<PageInfo<TUser>> getListByPage(Integer pageSize, Integer pageNum) {
        // 设置 当前页 及 每页大小
        PageHelper.startPage(pageNum, pageSize);
        // 查询所有数据，自动拼接 limit
        List<TUser> users = tUserMapper.selectUserList();
        // 转成 PageInfo
        PageInfo<TUser> pageInfo = new PageInfo<>(users);
        return Result.success(pageInfo);
    }

    @Override
    public Result<UserDetailsVO> selectUserDetailsById(Integer id) {
        UserDetailsVO tUser = tUserMapper.selectUserDetails(id);
        tUser.setPassword("");
        return Result.success(tUser);
    }

    @Override
    public Result<TUser> selectUserById(Integer id) {
        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        tUser.setPassword("");
        return Result.success(tUser);
    }

    @Override
    public Result<String> addUser(TUser tUser, Authentication authentication) {
        // 获取当前用户
        SystemUser systemUser = (SystemUser) authentication.getPrincipal();
        // 设置创建用户
        tUser.setCreateBy(systemUser.getId());
        // 设置创建时间
        tUser.setCreateTime(new Date());
        // 添加
        tUserMapper.insertSelective(tUser);
        return Result.success();
    }

    @Override
    public Result<String> editUser(TUser tUser, Authentication authentication) {
        // 获取当前用户
        SystemUser systemUser = (SystemUser) authentication.getPrincipal();
        // 更新编辑用户
        tUser.setEditBy(systemUser.getId());
        // 更新编辑时间
        tUser.setEditTime(new Date());
        // 更新
        tUserMapper.updateByPrimaryKeySelective(tUser);
        return Result.success();
    }

    @Override
    public Result<String> deleteUserById(Integer id) {
        // 删除用户
        tUserMapper.deleteByPrimaryKey(id);
        return Result.success();
    }

    @Override
    public Result<String> batchDelete(IdListVO ids) {
        if (ids == null || ids.getIds() == null || ids.getIds().isEmpty()) {
            return Result.fail();
        }
        // 删除
        int count = tUserMapper.batchDelete(ids.getIds());
        return count >= ids.getIds().size() ? Result.success() : Result.fail();
    }

}
