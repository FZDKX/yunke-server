package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TCustomerRemark;
import com.fzdkx.yunke.bean.vo.CustomerRemarkVO;

import java.util.List;

public interface TCustomerRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerRemark record);

    int insertSelective(TCustomerRemark record);

    TCustomerRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCustomerRemark record);

    int updateByPrimaryKey(TCustomerRemark record);

    List<CustomerRemarkVO> selectListByCustomerId(Integer customerId);

    int deleteById(Integer id);

    void deleteByCustomerId(Integer id);
}