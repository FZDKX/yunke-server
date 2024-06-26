package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TTran;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 发着呆看星
 * @create 2024/6/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TranVO extends TTran {
    private String ownerName;
    private String stageName;
    private TranCustomerVO tranCustomerVO;
}
