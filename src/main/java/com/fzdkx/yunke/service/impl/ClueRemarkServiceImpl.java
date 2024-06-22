package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TClueRemark;
import com.fzdkx.yunke.bean.vo.ClueRemarkVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.mapper.TClueRemarkMapper;
import com.fzdkx.yunke.service.ClueRemarkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/22
 */
@Service
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Resource
    private TClueRemarkMapper tClueRemarkMapper;

    @Override
    public Result<PageInfo<ClueRemarkVO>> remarkPage(Integer clueId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ClueRemarkVO> clueRemarkVOList = tClueRemarkMapper.selectListByClueId(clueId);
        PageInfo<ClueRemarkVO> pageInfo = new PageInfo<>(clueRemarkVOList);
        return Result.success(pageInfo);
    }

    @Override
    public Result<TClueRemark> getRemark(Integer id) {
        TClueRemark tClueRemark = tClueRemarkMapper.selectByPrimaryKey(id);
        return Result.success(tClueRemark);
    }

    @Override
    public Result<TClueRemark> editRemark(TClueRemark tClueRemark) {
        if (tClueRemark != null && tClueRemark.getId() != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            // 设置编辑人
            tClueRemark.setEditBy(loginUser.getTUser().getId());
            // 设置编辑时间
            tClueRemark.setEditTime(new Date());
            // 修改
            tClueRemarkMapper.updateByPrimaryKeySelective(tClueRemark);
        }
        return Result.success();
    }

    @Override
    public Result<TClueRemark> addRemark(TClueRemark tClueRemark) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 设置跟踪人
        tClueRemark.setCreateBy(loginUser.getTUser().getId());
        // 设置跟踪时间
        tClueRemark.setCreateTime(new Date());
        // 设置为0，未删除
        tClueRemark.setDeleted(0);
        tClueRemarkMapper.insertSelective(tClueRemark);
        return Result.success();
    }

    @Override
    public Result<String> deleteRemark(Integer id) {
        if (id != null) {
            tClueRemarkMapper.deleteById(id);
        }
        return Result.success();
    }
}
