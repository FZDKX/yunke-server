package com.fzdkx.yunke.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/14
 * 用户的权限信息VO
 */
@Data
public class PermAllVO {

    // 权限id
    private Integer id;

    // 权限名称
    private String name;

    // 子权限
    private List<PermAllVO> children;

    public PermAllVO(Integer id) {
        this.id = id;
    }
}
