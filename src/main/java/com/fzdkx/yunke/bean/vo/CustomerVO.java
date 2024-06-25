package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TCustomer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 发着呆看星
 * @create 2024/6/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerVO extends TCustomer {
    private String productName;
    private String intentionStateName;
    private ClueVO clueVO;

}
