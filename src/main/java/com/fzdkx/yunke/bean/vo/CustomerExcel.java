package com.fzdkx.yunke.bean.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fzdkx.yunke.config.converter.CustomerStateConverter;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 发着呆看星
 * @create 2024/6/23
 */
@Data
public class CustomerExcel {
    @ExcelProperty("所属人")
    private String ownerName;
    @ExcelProperty("活动名称")
    private String activityName;
    @ExcelProperty("客户姓名")
    private String fullName;
    @ExcelProperty("客户称呼")
    private String appellationName;
    @ExcelProperty("手机号")
    private String phone;
    @ExcelProperty("微信号")
    private String weixin;
    @ExcelProperty("QQ号")
    private String qq;
    @ExcelProperty("邮箱")
    private String email;
    @ExcelProperty("年龄")
    private Integer age;
    @ExcelProperty("职业")
    private String job;
    @ExcelProperty("年收入")
    private BigDecimal yearIncome;
    @ExcelProperty("地址")
    private String address;
    @ExcelProperty("是否贷款")
    private String needLoanName;
    @ExcelProperty("产品名称")
    private String productName;
    @ExcelProperty("客户来源")
    private String sourceName;
    @ExcelProperty("客户描述")
    private String description;
    @ExcelProperty("下次联系时间")
    private Date nextContactTime;
    @ExcelProperty(value = "线索状态", converter = CustomerStateConverter.class)
    private Integer state;
}
