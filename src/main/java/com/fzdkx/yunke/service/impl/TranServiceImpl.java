package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.*;
import com.fzdkx.yunke.bean.query.SQLQuery;
import com.fzdkx.yunke.bean.vo.TranHistoryVO;
import com.fzdkx.yunke.bean.vo.TranVO;
import com.fzdkx.yunke.common.DictionaryConstant;
import com.fzdkx.yunke.mapper.*;
import com.fzdkx.yunke.service.TranService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 发着呆看星
 * @create 2024/6/25
 */
@Service
public class TranServiceImpl implements TranService {

    @Resource
    private TProductMapper tProductMapper;
    @Resource
    private TCustomerMapper tCustomerMapper;
    @Resource
    private TTranMapper tTranMapper;
    @Resource
    private TTranHistoryMapper tTranHistoryMapper;
    @Resource
    private TTranRemarkMapper tTranRemarkMapper;

    @Override
    @Transactional
    public int sava(TTran tTran) {
        if (tTran.getCustomerId() == null) {
            return 0;
        }
        Date now = new Date();
        // 获取客户信息
        TCustomer tCustomer = tCustomerMapper.selectByPrimaryKey(tTran.getCustomerId());
        // 获取客户选择产品，获取经销商报价
        TProduct tProduct = tProductMapper.selectByPrimaryKey(tCustomer.getProduct());
        // 设置报价
        tTran.setMoney(tProduct.getQuotation());
        // 生成交易流水号
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 设置
        tTran.setTranNo(uuid);
        // 设置创建人，创建时间
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        tTran.setCreateBy(loginUser.getTUser().getId());
        tTran.setCreateTime(now);
        // 设置交易状态
        tTran.setStage(DictionaryConstant.TRAN_INIT);
        // 创建
        int count = tTranMapper.insertSelective(tTran);
        // 客户设置已交易
        tCustomer.setState(1);
        count += tCustomerMapper.updateByPrimaryKeySelective(tCustomer);
        // 创建交易历史
        TTranHistory history = new TTranHistory();
        BeanUtils.copyProperties(tTran, history);
        history.setTranId(tTran.getId());
        count += tTranHistoryMapper.insertSelective(history);
        return count;
    }

    @Override
    public PageInfo<TranVO> pageQuery(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TranVO> tranVOList = tTranMapper.selectList(new SQLQuery());
        return new PageInfo<>(tranVOList);
    }

    @Override
    public List<TranHistoryVO> getHistoryList(Integer tranId) {
        return tTranHistoryMapper.selectByTranId(tranId);
    }

    @Override
    public TTran getTran(Integer id) {
        return tTranMapper.selectByPrimaryKey(id);
    }

    @Override
    public int editStage(TTran tTran) {
        // 设置数据
        Date now = new Date();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 修改编辑时间
        tTran.setEditTime(now);
        tTran.setEditBy(loginUser.getTUser().getId());
        // 更新
        int count = tTranMapper.updateByPrimaryKeySelective(tTran);
        // 创建历史交易
        TTranHistory history = new TTranHistory();
        BeanUtils.copyProperties(tTran, history);
        history.setTranId(tTran.getId());
        history.setCreateBy(loginUser.getTUser().getId());
        history.setCreateTime(now);
        // 新增交易历史
        count += tTranHistoryMapper.insertSelective(history);
        return count;
    }

    @Override
    public int edit(TTran tTran) {
        // 设置数据
        Date now = new Date();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 修改编辑时间
        tTran.setEditTime(now);
        tTran.setEditBy(loginUser.getTUser().getId());
        return tTranMapper.updateByPrimaryKeySelective(tTran);
    }

    @Override
    @Transactional
    public int remove(Integer id) {
        // 删除相关历史记录
        tTranHistoryMapper.deleteByTranId(id);
        // 删除相关备注
        tTranRemarkMapper.deleteByTranId(id);
        // 获取当前交易记录
        TTran tTran = tTranMapper.selectByPrimaryKey(id);
        // 删除当前交易记录
        int count = tTranMapper.deleteByPrimaryKey(id);
        // 客户记录状态该变
        TCustomer tCustomer = new TCustomer();
        tCustomer.setId(tTran.getCustomerId());
        tCustomer.setState(DictionaryConstant.DELETE_TRAN);
        count += tCustomerMapper.updateByPrimaryKeySelective(tCustomer);
        return count;
    }
}
