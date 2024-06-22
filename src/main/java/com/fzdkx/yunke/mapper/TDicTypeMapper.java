package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TDicType;
import com.fzdkx.yunke.bean.vo.DicTypeVO;

import java.util.List;

public interface TDicTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDicType record);

    int insertSelective(TDicType record);

    TDicType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TDicType record);

    int updateByPrimaryKey(TDicType record);

    List<DicTypeVO> selectAllDicDataByCache();
}