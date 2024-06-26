package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TProduct;
import com.fzdkx.yunke.mapper.TProductMapper;
import com.fzdkx.yunke.service.ProductService;
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
 * @create 2024/6/20
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private TProductMapper tProductMapper;

    @Override
    public List<TProduct> loadAllProductByCache() {
        return tProductMapper.selectAll();
    }

    @Override
    public PageInfo<TProduct> pageList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TProduct> products = tProductMapper.selectList();
        return new PageInfo<>(products);
    }

    @Override
    public TProduct getProduct(Integer id) {
        return tProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public int editProduct(TProduct tProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        tProduct.setEditTime(new Date());
        tProduct.setEditBy(loginUser.getTUser().getId());
        return tProductMapper.updateByPrimaryKeySelective(tProduct);
    }

    @Override
    public int addProduct(TProduct tProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        tProduct.setCreateTime(new Date());
        tProduct.setCreateBy(loginUser.getTUser().getId());
        return tProductMapper.insertSelective(tProduct);
    }

    @Override
    public int updateProductState(Integer id, int state) {
        // 查询商品
        TProduct tProduct = tProductMapper.selectByPrimaryKey(id);
        // 修改为下架
        tProduct.setState(state);
        // 设置修改时间
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        tProduct.setEditTime(new Date());
        tProduct.setEditBy(loginUser.getTUser().getId());
        return tProductMapper.updateByPrimaryKeySelective(tProduct);
    }

}
