package com.fzdkx.yunke.bean.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限关系表
 * t_role_permission
 */
@Data
public class TRolePermission implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    private Integer permissionId;
}