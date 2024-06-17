package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TRole;
import com.fzdkx.yunke.bean.query.PermissionQuery;
import com.fzdkx.yunke.bean.vo.AllPermAndRoleVO;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.PermAllVO;
import com.fzdkx.yunke.common.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/14
 */
public interface RoleService {
    Result<PageInfo<TRole>> getListByPage(Integer pageSize, Integer pageNum);

    Result<List<PermAllVO>> getRolePerm(Integer id);
    PermAllVO convert(List<PermissionQuery> list, int curId);

    Result<AllPermAndRoleVO> queryUserDetail(Integer id);

    Result<String> editRole(TRole role, List<Integer> ids);

    Result<String> addRole(TRole role, List<Integer> ids);

    Result<String> deleteRole(Integer id);

    Result<String> batchDeleteRole(IdListVO idListVO);

    Result<List<TRole>> queryAllRoles();
}
