package com.fzdkx.yunke.config.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @author 发着呆看星
 * @create 2024/6/25
 */
public class CustomerStateConverter implements Converter<Integer> {
    @Override
    public WriteCellData<?> convertToExcelData(Integer value,
                                               ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) throws Exception {

        if (value == 0) {
            return new WriteCellData<>("客户");
        } else {
            return new WriteCellData<>("已转交易");
        }
    }

}
