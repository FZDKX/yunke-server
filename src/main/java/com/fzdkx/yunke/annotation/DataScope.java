package com.fzdkx.yunke.annotation;

import java.lang.annotation.*;

/**
 * @author 发着呆看星
 * @create 2024/6/18
 * 自定义注解：进行数据过滤，管理员可以对所有数据进行管理，而普通用户只对自己数据管理
 * 利用aop 获取 表名 和 字段 ，对当前用户进行判断
 * |-- 如果是普通用户，那么就拼接过滤条件，只操作当前用户数据
 * |-- 如果是管理员，那么就不拼接过滤条件
 */

@Documented  // 生成文档
@Target(ElementType.METHOD)  // 目标：作用在方法上
@Retention(RetentionPolicy.RUNTIME)  // 运行时生效
public @interface DataScope {
    /**
     * 表的别名
     */
    String tableAlias() default "";


    /**
     * 筛选条件：表中的字段
     */
    String tableField() default "";

    /**
     * 所需权限
     */
    String needPermission() default "";
}
