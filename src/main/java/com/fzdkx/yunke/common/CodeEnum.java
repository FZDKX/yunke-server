package com.fzdkx.yunke.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 发着呆看星
 * @create 2024/6/7
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum CodeEnum {

    SUCCESS(200, "操作成功"),

    FAIL(500, "操作失败"),

    TOKEN_IS_EMPTY(900, "token未携带"),

    TOKEN_IS_FALSIFY(901, "token被篡改"),

    TOKEN_IS_EXPIRED(903, "token已过期"),

    TWO_CLIENT_LOGIN(904, "已在另一个设备登录"),

    NO_AUTHORITY(910, "权限不足"),

    LOGIN_FAIL(911, "登录失败"),

    DATA_INTEGRITY_VIOLATION(912, "该数据具有其他引用"),

    DATA_ACCESS_ERROR(912, "数据操作错误"),

    SERVER_ERROR(912, "服务器异常");

    private int code;

    private String message;


}
