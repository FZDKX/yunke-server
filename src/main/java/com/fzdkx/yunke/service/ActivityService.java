package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TActivity;
import com.fzdkx.yunke.bean.query.ActivitySearchQuery;
import com.fzdkx.yunke.bean.vo.ActivityVO;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.common.Result;
import com.github.pagehelper.PageInfo;

/**
 * @author 发着呆看星
 * @create 2024/6/18
 */
public interface ActivityService {

    Result<PageInfo<ActivityVO>> activitySearchPage(ActivitySearchQuery activitySearchQuery);

    Result<TActivity> getActivityById(Integer id);

    Result<String> setActivity(TActivity tActivity);

    Result<String> addActivity(TActivity tActivity);

    Result<String> deleteActivity(Integer id);

    Result<String> batchDeleteActivity(IdListVO idListVO);
}
