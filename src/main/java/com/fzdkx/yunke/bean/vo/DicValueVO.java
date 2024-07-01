package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TDicValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 发着呆看星
 * @create 2024/6/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DicValueVO extends TDicValue {
    private String typeName;
}
