package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TTranRemark;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 发着呆看星
 * @create 2024/6/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TranRemarkVO extends TTranRemark {
    private String createName;
    private String editName;
    private String noteWayName;
}
