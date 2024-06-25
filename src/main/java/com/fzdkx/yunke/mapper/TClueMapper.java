package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TClue;
import com.fzdkx.yunke.bean.vo.ClueVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TClueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TClue record);

    int insertSelective(TClue record);

    TClue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TClue record);

    int updateByPrimaryKey(TClue record);

    List<ClueVO> selectList();

    void batchInsert(@Param("list") List<TClue> cachedDataList);

    ClueVO selectClueVO(Integer id);

}