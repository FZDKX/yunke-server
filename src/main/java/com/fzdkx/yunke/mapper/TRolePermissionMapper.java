package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRolePermission record);

    int insertSelective(TRolePermission record);

    TRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRolePermission record);

    int updateByPrimaryKey(TRolePermission record);

    void deletePermByRoleId(Integer id);

    void insertPerm(@Param("id") Integer id, @Param("ids") List<Integer> ids);
}