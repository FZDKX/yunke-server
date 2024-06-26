package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TTranRemark;
import com.fzdkx.yunke.bean.vo.TranRemarkVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.mapper.TTranRemarkMapper;
import com.fzdkx.yunke.service.TranRemarkService;
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
 * @create 2024/6/25
 */
@Service
public class TranRemarkServiceImpl implements TranRemarkService {
    @Resource
    private TTranRemarkMapper tTranRemarkMapper;

    @Override
    public Result<PageInfo<TranRemarkVO>> remarkPage(Integer tranId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TranRemarkVO> tranRemarkVOList = tTranRemarkMapper.selectListByTranId(tranId);
        PageInfo<TranRemarkVO> pageInfo = new PageInfo<>(tranRemarkVOList);
        return Result.success(pageInfo);
    }

    @Override
    public Result<TTranRemark> getRemark(Integer id) {
        TTranRemark tTranRemark = tTranRemarkMapper.selectByPrimaryKey(id);
        return Result.success(tTranRemark);
    }

    @Override
    public Result<String> editRemark(TTranRemark tTranRemark) {
        if (tTranRemark.getId() != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            // 设置编辑人
            tTranRemark.setEditBy(loginUser.getTUser().getId());
            // 设置编辑时间
            tTranRemark.setEditTime(new Date());
            // 修改
            tTranRemarkMapper.updateByPrimaryKeySelective(tTranRemark);
        }
        return Result.success();
    }

    @Override
    public Result<String> addRemark(TTranRemark tTranRemark) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 设置跟踪人
        tTranRemark.setCreateBy(loginUser.getTUser().getId());
        // 设置跟踪时间
        tTranRemark.setCreateTime(new Date());
        // 设置为0，未删除
        tTranRemark.setDeleted(0);
        tTranRemarkMapper.insertSelective(tTranRemark);
        return Result.success();
    }

    @Override
    public Result<String> deleteRemark(Integer id) {
        tTranRemarkMapper.deleteById(id);
        return Result.success();
    }
}
