package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.annotation.DataScope;
import com.fzdkx.yunke.bean.dao.TTran;
import com.fzdkx.yunke.bean.query.SQLQuery;
import com.fzdkx.yunke.bean.vo.TranVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TTranMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTran record);

    int insertSelective(TTran record);

    TTran selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTran record);

    int updateByPrimaryKey(TTran record);

    @DataScope(tableAlias = "tt", tableField = "create_by", needPermission = "tran:all")
    List<TranVO> selectList(@Param("sqlQuery") SQLQuery sqlQuery);
}