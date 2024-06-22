package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TPermission;
import com.fzdkx.yunke.bean.dao.TRole;
import com.fzdkx.yunke.bean.dao.TUser;
import com.fzdkx.yunke.bean.query.SQLQuery;
import com.fzdkx.yunke.bean.vo.*;
import com.fzdkx.yunke.common.RedisConstant;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.manager.RedisManager;
import com.fzdkx.yunke.mapper.TPermissionMapper;
import com.fzdkx.yunke.mapper.TRoleMapper;
import com.fzdkx.yunke.mapper.TUserMapper;
import com.fzdkx.yunke.mapper.TUserRoleMapper;
import com.fzdkx.yunke.service.UserService;
import com.fzdkx.yunke.utils.CacheUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private RedisManager redisManager;

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
        List<UserVO> users = tUserMapper.selectUserList(new SQLQuery());
        // 转成 PageInfo
        PageInfo<UserVO> pageInfo = new PageInfo<>(users);
        return Result.success(pageInfo);
    }

    @Override
    public Result<UserDetailsVO> selectUserDetailsById(Integer id) {
        UserDetailsVO tUser = tUserMapper.selectUserDetails(id);
        tUser.setPassword(null);
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
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 设置创建用户
        user.setCreateBy(loginUser.getTUser().getId());
        // 设置创建时间
        user.setCreateTime(new Date());
        // 添加用户
        tUserMapper.insertSelective(user);
        // 添加角色
        if (!ObjectUtils.isEmpty(user.getRoleIds())) {
            tUserRoleMapper.insertList(user.getRoleIds(), user.getId());
        }
        // 删除缓存中的 负责人数据
        redisManager.delete(RedisConstant.OWNER_PREFIX);
        return Result.success();
    }

    @Override
    @Transactional
    public Result<String> editUser(EditAddUserVO user, Authentication authentication) {
        user.setPassword(null);
        // 获取当前用户
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 更新编辑用户
        user.setEditBy(loginUser.getTUser().getId());
        // 更新编辑时间
        user.setEditTime(new Date());
        // 更新用户信息
        tUserMapper.updateByPrimaryKeySelective(user);
        // 更新角色信息
        // 删除所有角色
        tUserRoleMapper.deleteByUserId(user.getId());
        // 新增角色
        if (!ObjectUtils.isEmpty(user.getRoleIds())) {
            tUserRoleMapper.insertList(user.getRoleIds(), user.getId());
        }
        // 删除缓存中的 负责人数据
        redisManager.delete(RedisConstant.OWNER_PREFIX);
        return Result.success();
    }

    @Override
    @Transactional
    public Result<String> deleteUserById(Integer id) {
        // 清除用户与角色的关系
        tUserRoleMapper.deleteByUserId(id);
        // 删除用户
        tUserMapper.deleteByPrimaryKey(id);
        // 删除缓存中的 负责人数据
        redisManager.delete(RedisConstant.OWNER_PREFIX);
        return Result.success();
    }

    @Override
    public Result<String> batchDelete(IdListVO ids) {
        if (ids == null || ids.getIds() == null || ids.getIds().isEmpty()) {
            return Result.fail();
        }
        // 删除
        int count = tUserMapper.batchDelete(ids.getIds());
        // 删除缓存中的 负责人数据
        redisManager.delete(RedisConstant.OWNER_PREFIX);
        return count >= ids.getIds().size() ? Result.success() : Result.fail();
    }

    @Override
    public Result<List<String>> getUserPermButton(int id) {
        List<String> perms = tPermissionMapper.selectButtonCodeByUserId(id);
        return Result.success(perms);
    }

    @Override
    public List<TUser> queryAllOwner() {
        List<TUser> ownerList = CacheUtils.getCacheData(() -> {  // 从缓存中查询数据
            return redisManager.getListValue(RedisConstant.OWNER_PREFIX, TUser.class);
        }, () -> { // 从数据库中查询数据
            return tUserMapper.selectAllOwner();
        }, (data) -> { // 消费，将数据放入缓存中
            redisManager.setListValue(RedisConstant.OWNER_PREFIX, data);
            redisManager.setExpireTime(RedisConstant.OWNER_PREFIX, RedisConstant.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
        });
        return ownerList;
    }
}
