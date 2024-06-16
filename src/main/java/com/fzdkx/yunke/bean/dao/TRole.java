package com.fzdkx.yunke.bean.dao;

import java.io.Serializable;
import lombok.Data;

/**
 * 角色表
 * t_role
 */
@Data
public class TRole implements Serializable {
    private Integer id;

    /**
     * 角色
     */
    private String role;

    /**
     * 角色名
     */
    private String roleName;

    private static final long serialVersionUID = 1L;
}