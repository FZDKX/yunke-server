package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TCustomer;
import com.fzdkx.yunke.bean.vo.CustomerExcel;
import com.fzdkx.yunke.bean.vo.CustomerVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCustomer record);

    int insertSelective(TCustomer record);

    TCustomer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCustomer record);

    int updateByPrimaryKey(TCustomer record);


    List<CustomerVO> selectList();

    List<CustomerExcel> selectExcel(@Param("ids") List<Integer> ids);
}