package com.fzdkx.yunke.bean.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色表
 * t_role
 */
@Data
public class TRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 角色
     */
    private String role;
    /**
     * 角色名
     */
    private String roleName;
}