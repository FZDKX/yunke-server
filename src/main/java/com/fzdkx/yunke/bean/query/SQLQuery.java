package com.fzdkx.yunke.bean.query;

import lombok.Data;

/**
 * @author 发着呆看星
 * @create 2024/6/18
 * SQL语句的过滤条件
 */
@Data
public class SQLQuery {

    // 表.字段 = 当前用户id ，利用aop进行拼接
    private String filterSQL;
}
