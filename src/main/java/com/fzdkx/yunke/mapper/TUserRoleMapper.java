package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserRole record);

    int insertSelective(TUserRole record);

    TUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserRole record);

    int updateByPrimaryKey(TUserRole record);

    void insertList(@Param("roleIds") List<Integer> roleIds,@Param("userId") Integer id);

    void deleteByUserId(Integer id);
}