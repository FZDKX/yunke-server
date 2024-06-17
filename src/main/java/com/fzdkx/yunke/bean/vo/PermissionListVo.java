package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TPermission;
import lombok.Data;

/**
 * @author 发着呆看星
 * @create 2024/6/14
 */
@Data
public class PermissionListVo extends TPermission {
    // 子的个数
    private int count;
}
