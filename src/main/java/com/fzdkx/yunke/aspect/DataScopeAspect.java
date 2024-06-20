package com.fzdkx.yunke.aspect;

import com.fzdkx.yunke.annotation.DataScope;
import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TPermission;
import com.fzdkx.yunke.bean.query.SQLQuery;
import com.fzdkx.yunke.mapper.TRoleMapper;
import jakarta.annotation.Resource;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/18
 * 利用aop进行拦截 @DataScope 注解，拼接过滤sql
 */
@Aspect
@Component
@Data
public class DataScopeAspect {

    @Resource
    private TRoleMapper tRoleMapper;

    // 定义切点，作用在有 @DataScope 的方法上，那么所有杯@DataScope标识的方法都会被拦截
    @Pointcut(value = "@annotation(com.fzdkx.yunke.annotation.DataScope)")
    public void pointCut() {
    }

    // 处理方法
    @Around("pointCut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 通过签名获取方法
        Method method = methodSignature.getMethod();
        // 通过方法获取注解
        DataScope dataScope = method.getAnnotation(DataScope.class);
        // 获取注解数据：表名 ， 字段
        String tableAlias = dataScope.tableAlias();
        String tableField = dataScope.tableField();
        // 拼接过滤条件
        // 从security上下文获取当前用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 获取用户权限
        List<TPermission> permissions = loginUser.getTButtonPermissions();
        // 获取方法所需权限
        String needPermission = dataScope.needPermission();
        // 判断用户是否拥有所需权限
        boolean flag = false;
        for (TPermission permission : permissions) {
            // 如果拥有该权限，那么标记为true
            if (permission.getCode().equals(needPermission)) {
                flag = true;
                break;
            }
        }
        // 如果不是管理角色之一，那么就需要拼接过滤sql
        if (!flag) {
            // 将过滤filterSQL，放入SQLQuery中
            Object param = joinPoint.getArgs()[0];
            if (param instanceof SQLQuery) {
                SQLQuery sqlQuery = (SQLQuery) param;
                String filterSQL = " and " + tableAlias + "." + tableField + " = " + loginUser.getTUser().getId();
                sqlQuery.setFilterSQL(filterSQL);
            }
        }
        // 执行目标方法
        return joinPoint.proceed();
    }
}
