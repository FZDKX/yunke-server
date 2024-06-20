package com.fzdkx.yunke.common;

/**
 * @author 发着呆看星
 * @create 2024/6/11
 * redis常量
 */
public class RedisConstant {
    // 过期时间
    // 默认过期时间 一天
    public static final long DEFAULT_EXPIRE_TIME = 24 * 60 * 60;

    // 过期时间
    public static final long EXPIRE_TIME = 7 * 24 * 60 * 60;

    // 前缀
    // token前缀
    public static final String TOKEN_PREFIX = "token:";

    // 负责人前缀
    public static final String OWNER_PREFIX = "owner";
}
