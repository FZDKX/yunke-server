package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TRole;
import com.fzdkx.yunke.bean.query.PermissionQuery;

import java.util.List;

public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    int insertSelective(TRole record);

    TRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);

    List<TRole> selectByUserId(Integer id);

    List<TRole> selectList();

    List<PermissionQuery> selectRolePerm(Integer id);

    int batchDelete(List<Integer> ids);
}