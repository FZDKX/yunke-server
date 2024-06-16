package com.fzdkx.yunke.service;

import java.util.concurrent.TimeUnit;

/**
 * @author 发着呆看星
 * @create 2024/6/11
 */
public interface RedisService {
    void setValue(String key, String value);

    void setValue(String key, String value, long timeout, TimeUnit unit);

    void expire(String key, long timeout, TimeUnit unit);

    String get(String key);
}
