package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TCustomerRemark;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 发着呆看星
 * @create 2024/6/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerRemarkVO extends TCustomerRemark {
    private String createName;
    private String editName;
    private String noteWayName;
}
