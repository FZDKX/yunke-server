package com.fzdkx.yunke.bean.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户表
 * t_customer
 */
@Data
public class TCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键，自动增长，客户ID
     */
    private Integer id;
    /**
     * 线索ID
     */
    private Integer clueId;
    /**
     * 选购产品
     */
    private Integer product;
    /**
     * 意向状态
     */
    private Integer intentionState;
    /**
     * 客户描述
     */
    private String description;
    /**
     * 下次联系时间
     */
    private Date nextContactTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private Integer createBy;
    /**
     * 编辑时间
     */
    private Date editTime;
    /**
     * 编辑人
     */
    private Integer editBy;
    /**
     * 客户状态
     */
    private Integer state;
}