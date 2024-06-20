package com.fzdkx.yunke.bean.query;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 发着呆看星
 * @create 2024/6/18
 */
@Data
public class ActivitySearchQuery {
    private Integer ownerId;
    private String name;
    private BigDecimal minCost;
    private BigDecimal maxCost;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityMinTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityMaxTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createMinTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createMaxTime;
    private Integer pageNum;
    private Integer pageSize;
}
