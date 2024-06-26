package com.fzdkx.yunke.bean.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关系表
 * t_user_role
 */
@Data
public class TUserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private Integer roleId;
}