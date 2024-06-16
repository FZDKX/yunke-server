package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 发着呆看星
 * @create 2024/6/11
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate template;

    @Override
    public void setValue(String key, String value) {
        template.opsForValue().set(key, value);
    }

    @Override
    public void setValue(String key, String value, long timeout, TimeUnit unit) {
        template.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public void expire(String key, long timeout, TimeUnit unit) {
        template.expire(key, timeout, unit);
    }

    @Override
    public String get(String key) {
        return template.opsForValue().get(key);
    }
}
