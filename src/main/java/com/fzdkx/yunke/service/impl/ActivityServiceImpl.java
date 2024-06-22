package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TActivity;
import com.fzdkx.yunke.bean.query.ActivitySearchQuery;
import com.fzdkx.yunke.bean.query.SQLQuery;
import com.fzdkx.yunke.bean.vo.ActivityVO;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.mapper.TActivityMapper;
import com.fzdkx.yunke.mapper.TActivityRemarkMapper;
import com.fzdkx.yunke.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/18
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private TActivityMapper tActivityMapper;

    @Resource
    private TActivityRemarkMapper tActivityRemarkMapper;

    @Override
    public Result<PageInfo<ActivityVO>> activitySearchPage(ActivitySearchQuery query) {
        // 设置 当前页 及 每页大小
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        // 查询所有数据，自动拼接 limit
        List<ActivityVO> activityVOList = tActivityMapper.selectFilterList(new SQLQuery(), query);
        // 转成 PageInfo
        PageInfo<ActivityVO> pageInfo = new PageInfo<>(activityVOList);
        return Result.success(pageInfo);
    }

    @Override
    public Result<TActivity> getActivityById(Integer id) {
        TActivity tActivity = tActivityMapper.selectByPrimaryKey(id);
        return Result.success(tActivity);
    }

    @Override
    public Result<String> setActivity(TActivity tActivity) {
        // 修改编辑时间
        tActivity.setEditTime(new Date());
        // 修改编辑人
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        tActivity.setEditBy(loginUser.getTUser().getId());
        // 修改数据库
        tActivityMapper.updateByPrimaryKeySelective(tActivity);
        return Result.success();
    }

    @Override
    public Result<String> addActivity(TActivity tActivity) {
        // 设置创建时间
        tActivity.setCreateTime(new Date());
        // 设置创建人
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        tActivity.setCreateBy(loginUser.getTUser().getId());
        // 新增
        tActivityMapper.insertSelective(tActivity);
        return Result.success();
    }

    @Override
    public Result<String> deleteActivity(Integer id) {
        // 删除备注
        tActivityRemarkMapper.deleteByActivityId(id);
        // 删除市场活动
        tActivityMapper.deleteByPrimaryKey(id);
        return Result.success();
    }

    @Override
    public Result<String> batchDeleteActivity(IdListVO idListVO) {
        if(idListVO == null || ObjectUtils.isEmpty(idListVO.getIds())){
            return Result.success();
        }
        // 批量删除备注
        tActivityRemarkMapper.batchDeleteByActivityIds(idListVO.getIds());
        // 批量删除市场活动
        tActivityMapper.batchDeleteByIds(idListVO.getIds());
        return Result.success();
    }

    @Override
    public List<TActivity> loadAllOngoingActivity() {
        return tActivityMapper.selectAllOngoingCache();
    }
}
