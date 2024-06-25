package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TCustomerRemark;
import com.fzdkx.yunke.bean.vo.CustomerRemarkVO;
import com.fzdkx.yunke.mapper.TCustomerRemarkMapper;
import com.fzdkx.yunke.service.CustomerRemarkService;
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
public class CustomerRemarkServiceImpl implements CustomerRemarkService {

    @Resource
    private TCustomerRemarkMapper tCustomerRemarkMapper;

    @Override
    public PageInfo<CustomerRemarkVO> remarkPage(Integer customerId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CustomerRemarkVO> customerRemarkVOList = tCustomerRemarkMapper.selectListByCustomerId(customerId);
        return new PageInfo<>(customerRemarkVOList);
    }

    @Override
    public TCustomerRemark getRemark(Integer id) {
        return tCustomerRemarkMapper.selectByPrimaryKey(id);
    }

    @Override
    public void editRemark(TCustomerRemark tCustomerRemark) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        tCustomerRemark.setEditBy(loginUser.getTUser().getId());
        tCustomerRemark.setEditTime(new Date());
        tCustomerRemarkMapper.updateByPrimaryKeySelective(tCustomerRemark);
    }

    @Override
    public void addRemark(TCustomerRemark tCustomerRemark) {
        tCustomerRemark.setDeleted(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        tCustomerRemark.setCreateBy(loginUser.getTUser().getId());
        tCustomerRemark.setCreateTime(new Date());
        tCustomerRemarkMapper.insertSelective(tCustomerRemark);
    }

    @Override
    public int deleteRemark(Integer id) {
        return tCustomerRemarkMapper.deleteById(id);
    }
}
