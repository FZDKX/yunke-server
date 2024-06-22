package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TDicType;
import com.fzdkx.yunke.bean.dao.TDicValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DicTypeVO extends TDicType {

    // 该类型下的所有数据
    List<TDicValue> values;

}
