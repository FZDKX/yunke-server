package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TTranRemark;
import com.fzdkx.yunke.bean.vo.TranRemarkVO;

import java.util.List;

public interface TTranRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTranRemark record);

    int insertSelective(TTranRemark record);

    TTranRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTranRemark record);

    int updateByPrimaryKey(TTranRemark record);

    void deleteByTranId(Integer id);

    List<TranRemarkVO> selectListByTranId(Integer tranId);

    int deleteById(Integer id);
}