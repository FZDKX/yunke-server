package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TProduct;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 */
public interface ProductService {
    List<TProduct> loadAllProductByCache();

    PageInfo<TProduct> pageList(Integer pageNum, Integer pageSize);

    TProduct getProduct(Integer id);

    int editProduct(TProduct tProduct);

    int addProduct(TProduct tProduct);

    int updateProductState(Integer id, int state);
}
