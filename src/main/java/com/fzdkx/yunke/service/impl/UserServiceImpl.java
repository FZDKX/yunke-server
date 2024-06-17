package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.*;
import com.fzdkx.yunke.bean.vo.*;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.mapper.TPermissionMapper;
import com.fzdkx.yunke.mapper.TRoleMapper;
import com.fzdkx.yunke.mapper.TUserMapper;
import com.fzdkx.yunke.mapper.TUserRoleMapper;
import com.fzdkx.yunke.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private TUserRoleMapper tUserRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库在查询用户信息
        TUser tUser = tUserMapper.selectByUsername(username);
        // 从数据库中查询用户角色信息
        List<TRole> roleList = roleMapper.selectByUserId(tUser.getId());
        // 从数据库中查询用户 菜单权限信息
        List<PermissionVO> menuList = tPermissionMapper.selectMenuByUserId(tUser.getId());
        // 从数据库中查询用户 按钮权限信息
        List<TPermission> buttenList = tPermissionMapper.selectButtonByUserId(tUser.getId());
        return new LoginUser(tUser, roleList, menuList, buttenList);
    }

    @Override
    public Result<PageInfo<UserVO>> getListByPage(Integer pageSize, Integer pageNum) {
        // 设置 当前页 及 每页大小
        PageHelper.startPage(pageNum, pageSize);
        // 查询所有数据，自动拼接 limit
        List<UserVO> users = tUserMapper.selectUserList();
        // 转成 PageInfo
        PageInfo<UserVO> pageInfo = new PageInfo<>(users);
        return Result.success(pageInfo);
    }

    @Override
    public Result<UserDetailsVO> selectUserDetailsById(Integer id) {
        UserDetailsVO tUser = tUserMapper.selectUserDetails(id);
        tUser.setPassword("");
        return Result.success(tUser);
    }

    @Override
    public Result<UserVO> selectUserById(Integer id) {
        UserVO userVO = tUserMapper.selectUserAndRole(id);
        userVO.setPassword("");
        return Result.success(userVO);
    }

    @Override
    @Transactional
    public Result<String> addUser(EditAddUserVO user, Authentication authentication) {
        // 获取当前用户
        SystemUser systemUser = (SystemUser) authentication.getPrincipal();
        // 设置创建用户
        user.setCreateBy(systemUser.getId());
        // 设置创建时间
        user.setCreateTime(new Date());
        // 添加用户
        tUserMapper.insertSelective(user);
        // 添加角色
        List<Integer> roleIds = user.getRoleIds();
        if (roleIds != null) {
            tUserRoleMapper.insertList(roleIds, user.getId());
        }
        return Result.success();
    }

    @Override
    @Transactional
    public Result<String> editUser(EditAddUserVO user, Authentication authentication) {
        // 获取当前用户
        SystemUser systemUser = (SystemUser) authentication.getPrincipal();
        // 更新编辑用户
        user.setEditBy(systemUser.getId());
        // 更新编辑时间
        user.setEditTime(new Date());
        // 更新用户信息
        tUserMapper.updateByPrimaryKeySelective(user);
        // 更新角色信息
        // 删除所有角色
        tUserRoleMapper.deleteByUserId(user.getId());
        // 新增角色
        if (user.getRoleIds() != null) {
            tUserRoleMapper.insertList(user.getRoleIds(), user.getId());
        }
        return Result.success();
    }

    @Override
    @Transactional
    public Result<String> deleteUserById(Integer id) {
        // 清除用户与角色的关系
        tUserRoleMapper.deleteByUserId(id);
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
