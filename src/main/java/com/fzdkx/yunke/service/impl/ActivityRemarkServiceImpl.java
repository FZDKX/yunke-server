package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TActivityRemark;
import com.fzdkx.yunke.bean.vo.ActivityRemarkVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.mapper.TActivityRemarkMapper;
import com.fzdkx.yunke.service.ActivityRemarkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/19
 */
@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Resource
    private TActivityRemarkMapper tActivityRemarkMapper;

    @Override
    public Result<PageInfo<ActivityRemarkVO>> remarkPage(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ActivityRemarkVO> activityRemarkVOList = tActivityRemarkMapper.selectListByActivityId(id);
        PageInfo<ActivityRemarkVO> pageInfo = new PageInfo<>(activityRemarkVOList);
        return Result.success(pageInfo);
    }

    @Override
    public Result<TActivityRemark> getRemark(Integer id) {
        TActivityRemark tActivityRemark = tActivityRemarkMapper.selectByPrimaryKey(id);
        return Result.success(tActivityRemark);
    }

    @Override
    public Result<TActivityRemark> editRemark(TActivityRemark tActivityRemark) {
        if (tActivityRemark == null || tActivityRemark.getActivityId() == null || tActivityRemark.getId() == null) {
            return Result.fail();
        }
        // 设置编辑人
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        tActivityRemark.setEditBy(loginUser.getTUser().getId());
        // 设置编辑时间
        tActivityRemark.setEditTime(new Date());
        tActivityRemarkMapper.updateByPrimaryKeySelective(tActivityRemark);
        return Result.success();
    }

    @Override
    @Transactional
    public Result<TActivityRemark> addRemark(TActivityRemark tActivityRemark) {
        if (tActivityRemark == null || tActivityRemark.getActivityId() == null) {
            return Result.fail();
        }
        // 设置创建人
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        tActivityRemark.setCreateBy(loginUser.getTUser().getId());
        // 设置创建时间
        tActivityRemark.setCreateTime(new Date());
        // 设置逻辑删除为0，即不删除
        tActivityRemark.setDeleted(0);
        // 创建
        tActivityRemarkMapper.insert(tActivityRemark);
        return Result.success();
    }

    @Override
    public Result<String> deleteRemark(Integer id) {
        tActivityRemarkMapper.deleteById(id);
        return Result.success();
    }
}
