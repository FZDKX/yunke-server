package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TActivityRemark;
import com.fzdkx.yunke.bean.vo.RemarkVO;

import java.util.List;

public interface TActivityRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TActivityRemark record);

    int insertSelective(TActivityRemark record);

    TActivityRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TActivityRemark record);

    int updateByPrimaryKey(TActivityRemark record);

    int deleteByActivityId(Integer id);

    int batchDeleteByActivityIds(List<Integer> ids);

    List<RemarkVO> selectListByActivityId(Integer id);

    void deleteById(Integer id);
}