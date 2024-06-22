package com.fzdkx.yunke.config.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.fzdkx.yunke.YunKeApplication;
import com.fzdkx.yunke.bean.dao.TDicValue;
import com.fzdkx.yunke.common.DictionaryConstant;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 */
public class StateConverter implements Converter<Integer> {
    /**
     * @param cellData 从excel中读取到的内容
     * @return 返回转成的内容
     */
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData,
                                     ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) throws Exception {
        // 获取读取到的内容
        String value = cellData.getStringValue();
        // 从缓存中获取该中类型的所有数据，进行匹配，看看转换成什么
        List<TDicValue> tDicValueList = (List<TDicValue>) YunKeApplication.cacheMap.get(DictionaryConstant.CLUE_STATE);
        // 如果匹配到，那么就转换
        for (TDicValue tDicValue : tDicValueList) {
            if (tDicValue.getTypeValue().equals(value)) {
                return tDicValue.getId();
            }
        }
        // 未找到，转成-1
        return -1;
    }
}
