package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.TRole;
import com.fzdkx.yunke.bean.query.PermissionQuery;
import com.fzdkx.yunke.bean.vo.AllPermAndRoleVO;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.PermAllVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.mapper.TPermissionMapper;
import com.fzdkx.yunke.mapper.TRoleMapper;
import com.fzdkx.yunke.mapper.TRolePermissionMapper;
import com.fzdkx.yunke.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/14
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private TRoleMapper tRoleMapper;

    @Resource
    private TPermissionMapper tPermissionMapper;

    @Resource
    private TRolePermissionMapper tRolePermissionMapper;

    @Override
    public Result<PageInfo<TRole>> getListByPage(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<TRole> roles = tRoleMapper.selectList();
        PageInfo<TRole> pageInfo = new PageInfo<>(roles);
        return Result.success(pageInfo);
    }


    @Override
    public Result<List<PermAllVO>> getRolePerm(Integer id) {
        // 查询角色的所有权限
        List<PermissionQuery> list = tRoleMapper.selectRolePerm(id);
        // 封装成 RolePermVO
        PermAllVO rolePermAllVO = convert(list, 0);
        return Result.success(rolePermAllVO.getChildren());
    }

    @Override
    public PermAllVO convert(List<PermissionQuery> list, int curId) {
        PermAllVO rolePermAllVO = new PermAllVO(curId);
        // 创建存储子节点的集合
        ArrayList<PermAllVO> rolePermAllVOList = new ArrayList<>();
        // 遍历所有节点
        for (PermissionQuery node : list) {
            // 如果节点的父节点为 当前节点，那么就递归该节点
            if (node.getParentId() == curId) {
                rolePermAllVOList.add(convert(list, node.getId()));
            }
            // 如果节点就为当前节点，那么就为name赋值
            else if (node.getId() == curId) {
                rolePermAllVO.setName(node.getName());
            }
        }
        rolePermAllVO.setChildren(rolePermAllVOList);
        return rolePermAllVO;
    }

    @Override
    public Result<AllPermAndRoleVO> queryUserDetail(Integer id) {
        // 返回结果
        AllPermAndRoleVO allPermAndRoleVO = new AllPermAndRoleVO();
        // 查询所有权限
        List<PermissionQuery> all = tPermissionMapper.selectAll();
        // 转换
        PermAllVO permAllVO = convert(all, 0);
        // 设置进入结果集
        allPermAndRoleVO.setRolePermAllVOList(permAllVO.getChildren());
        // 获取角色已拥有的权限
        List<PermissionQuery> list = tRoleMapper.selectRolePerm(id);
        // 获取权限id
        ArrayList<Integer> ids = new ArrayList<>();
        list.forEach(item -> ids.add(item.getId()));
        allPermAndRoleVO.setIds(ids);
        // 获取角色信息
        TRole tRole = tRoleMapper.selectByPrimaryKey(id);
        allPermAndRoleVO.setRole(tRole);
        // 返回结果
        return Result.success(allPermAndRoleVO);
    }

    @Override
    @Transactional
    public Result<String> editRole(TRole role, List<Integer> ids) {
        if (role == null || role.getId() == null) {
            return Result.fail();
        }
        // 修改角色信息
        tRoleMapper.updateByPrimaryKeySelective(role);
        // 修改权限信息
        // 删除原有权限
        tRolePermissionMapper.deletePermByRoleId(role.getId());
        if (!ids.isEmpty()) {
            // 新增现在权限
            tRolePermissionMapper.insertPerm(role.getId(), ids);
        }
        return Result.success();
    }

    @Override
    @Transactional
    public Result<String> addRole(TRole role, List<Integer> ids) {
        if (role == null || role.getId() != null) {
            return Result.fail();
        }
        tRoleMapper.insert(role);
        if (!ids.isEmpty()) {
            tRolePermissionMapper.insertPerm(role.getId(), ids);
        }
        return Result.success();
    }

    @Override
    public Result<String> deleteRole(Integer id) {
        // 先删除相关联的权限记录
        tRolePermissionMapper.deletePermByRoleId(id);
        // 在删除角色
        tRoleMapper.deleteByPrimaryKey(id);
        return Result.success();
    }

    @Override
    public Result<String> batchDeleteRole(IdListVO ids) {
        if (ids == null || ids.getIds() == null || ids.getIds().isEmpty()) {
            return Result.fail();
        }
        // 删除
        int count = tRoleMapper.batchDelete(ids.getIds());
        return count >= ids.getIds().size() ? Result.success() : Result.fail();
    }

    @Override
    public Result<List<TRole>> queryAllRoles() {
        List<TRole> roleList = tRoleMapper.selectAll();
        return Result.success(roleList);
    }
}
