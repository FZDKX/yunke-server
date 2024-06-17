package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TRole;
import lombok.Data;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/14
 */
@Data
public class AllPermAndRoleVO {
    // 所有权限
    private List<PermAllVO> rolePermAllVOList;
    // 角色拥有权限
    private List<Integer> ids;
    // 角色
    private TRole role;
}
