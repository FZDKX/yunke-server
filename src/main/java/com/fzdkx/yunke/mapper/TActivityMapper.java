package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.annotation.DataScope;
import com.fzdkx.yunke.bean.dao.TActivity;
import com.fzdkx.yunke.bean.query.ActivitySearchQuery;
import com.fzdkx.yunke.bean.query.SQLQuery;
import com.fzdkx.yunke.bean.vo.ActivityVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TActivity record);

    int insertSelective(TActivity record);

    TActivity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TActivity record);

    int updateByPrimaryKey(TActivity record);

    @DataScope(tableAlias = "ta", tableField = "owner_id", needPermission = "activity:all")
    List<ActivityVO> selectFilterList(@Param("sqlQuery") SQLQuery sqlQuery, @Param("query") ActivitySearchQuery query);

    int batchDeleteByIds(List<Integer> ids);

    List<TActivity> selectAllOngoingCache();
}