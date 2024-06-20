package com.fzdkx.yunke.bean.dao;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户表
 * t_user
 */
@Data
public class TUser implements Serializable {
    /**
     * 主键，自动增长，用户ID
     */
    private Integer id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户手机
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 账号是否启用，0禁用 1启用
     */
    private Integer accountEnabled;

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
     * 最近登录时间
     */
    private Date lastLoginTime;

    public void dataDesensitization() {
        this.password = null;
        this.phone = null;
        this.email = null;
    }

    private static final long serialVersionUID = 1L;
}