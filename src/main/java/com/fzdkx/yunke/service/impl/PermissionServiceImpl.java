package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.TPermission;
import com.fzdkx.yunke.bean.query.PermissionQuery;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.PermVO;
import com.fzdkx.yunke.bean.vo.PermissionViewVo;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.mapper.TPermissionMapper;
import com.fzdkx.yunke.service.PermissionService;
import com.fzdkx.yunke.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/14
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private RoleService roleService;
    @Resource
    private TPermissionMapper tPermissionMapper;

    @Override
    public Result<PageInfo<PermissionViewVo>> pageQueryTopMenu(Integer pageNum, Integer pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNum, pageSize);
        // 查询最顶层的记录
        List<PermissionViewVo> permissions = tPermissionMapper.selectChildren(0);
        // 获取pageInfo
        PageInfo<PermissionViewVo> pageInfo = new PageInfo<>(permissions);
        return Result.success(pageInfo);
    }

    @Override
    public Result<List<PermissionViewVo>> queryChildren(Integer parentId) {
        List<PermissionViewVo> permissionList = tPermissionMapper.selectChildren(parentId);
        return Result.success(permissionList);
    }

    @Override
    public Result<List<PermVO>> queryAll() {
        List<PermissionQuery> list = tPermissionMapper.selectAll();
        PermVO permVO = roleService.convert(list, 0);
        return Result.success(permVO.getChildren());
    }

    @Override
    public Result<TPermission> query(Integer id) {
        TPermission tPermission = tPermissionMapper.selectByPrimaryKey(id);
        return Result.success(tPermission);
    }

    @Override
    public Result<String> edit(TPermission tPermission) {
        tPermissionMapper.updateByPrimaryKeySelective(tPermission);
        return Result.success();
    }

    @Override
    public Result<String> add(TPermission tPermission) {
        if (tPermission == null || tPermission.getParentId() == null) {
            Result.fail();
        }
        tPermissionMapper.insertSelective(tPermission);
        return Result.success();
    }

    @Override
    public Result<String> deleteById(Integer id) {
        int count = tPermissionMapper.deleteByPrimaryKey(id);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @Override
    public Result<String> batchDelete(IdListVO idListVO) {
        if (idListVO == null || idListVO.getIds() == null || idListVO.getIds().isEmpty()) {
            return Result.fail();
        }
        int size = idListVO.getIds().size();
        int count = tPermissionMapper.batchDelete(idListVO.getIds());
        return count >= size ? Result.success() : Result.fail();
    }
}
