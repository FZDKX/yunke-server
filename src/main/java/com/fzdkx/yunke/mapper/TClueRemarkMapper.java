package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TClueRemark;
import com.fzdkx.yunke.bean.vo.ClueRemarkVO;

import java.util.List;

public interface TClueRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TClueRemark record);

    int insertSelective(TClueRemark record);

    TClueRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TClueRemark record);

    int updateByPrimaryKey(TClueRemark record);

    void deleteByClueId(Integer id);

    List<ClueRemarkVO> selectListByClueId(Integer clueId);

    void deleteById(Integer id);
}