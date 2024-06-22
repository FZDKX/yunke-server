package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TProduct;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 */
public interface ProductService {
    List<TProduct> loadAllProductByCache();

}
