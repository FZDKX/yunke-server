package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TActivityRemark;
import com.fzdkx.yunke.bean.vo.ActivityRemarkVO;
import com.fzdkx.yunke.common.Result;
import com.github.pagehelper.PageInfo;

/**
 * @author 发着呆看星
 * @create 2024/6/19
 */
public interface ActivityRemarkService {
    Result<PageInfo<ActivityRemarkVO>> remarkPage(Integer id, Integer pageNum, Integer pageSize);

    Result<TActivityRemark> getRemark(Integer id);

    Result<TActivityRemark> editRemark(TActivityRemark tActivityRemark);

    Result<TActivityRemark> addRemark(TActivityRemark tActivityRemark);

    Result<String> deleteRemark(Integer id);
}
