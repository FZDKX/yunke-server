package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TSystemInfo;

public interface TSystemInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TSystemInfo record);

    int insertSelective(TSystemInfo record);

    TSystemInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TSystemInfo record);

    int updateByPrimaryKey(TSystemInfo record);
}