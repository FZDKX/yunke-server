package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TDicValue;
import com.fzdkx.yunke.bean.vo.DicValueVO;

import java.util.List;

public interface TDicValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDicValue record);

    int insertSelective(TDicValue record);

    TDicValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TDicValue record);

    int updateByPrimaryKey(TDicValue record);

    void deleteByDicType(String typeCode);

    List<DicValueVO> selectList(Integer pageNum, Integer pageSize);

    DicValueVO selectById(Integer id);
}