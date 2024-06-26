package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TTranHistory;
import com.fzdkx.yunke.bean.vo.TranHistoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TTranHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTranHistory record);

    int insertSelective(TTranHistory record);

    TTranHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTranHistory record);

    int updateByPrimaryKey(TTranHistory record);

    List<TranHistoryVO> selectByTranId(@Param("tranId") Integer tranId);

    void deleteByTranId(Integer id);
}