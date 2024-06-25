package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TCustomer;
import com.fzdkx.yunke.bean.vo.CustomerExcel;
import com.fzdkx.yunke.bean.vo.CustomerVO;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/22
 */
public interface CustomerService {
    int save(TCustomer tCustomer);

    PageInfo<CustomerVO> pageQuery(Integer pageNum, Integer pageSize);

    TCustomer getCustomer(Integer id);

    void editCustomer(TCustomer tCustomer);

    int remove(Integer id);

    List<CustomerExcel> exportAll();

    List<CustomerExcel> exportSelect(List<Integer> ids);

    void exportExcel(HttpServletResponse response, List<CustomerExcel> customerExcels) throws IOException, InterruptedException;
}
