package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TRole;
import lombok.Data;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/15
 */
@Data
public class RoleInfoVO {
    private TRole role;
    private List<Integer> ids;
}
