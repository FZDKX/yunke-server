package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TCustomerRemark;
import com.fzdkx.yunke.bean.vo.CustomerRemarkVO;
import com.github.pagehelper.PageInfo;

/**
 * @author 发着呆看星
 * @create 2024/6/22
 */
public interface CustomerRemarkService {
    PageInfo<CustomerRemarkVO> remarkPage(Integer customerId, Integer pageNum, Integer pageSize);

    TCustomerRemark getRemark(Integer id);

    void editRemark(TCustomerRemark tCustomerRemark);

    void addRemark(TCustomerRemark tCustomerRemark);

    int deleteRemark(Integer id);
}
