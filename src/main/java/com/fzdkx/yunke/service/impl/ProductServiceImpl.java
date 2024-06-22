package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.TProduct;
import com.fzdkx.yunke.mapper.TProductMapper;
import com.fzdkx.yunke.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
}
