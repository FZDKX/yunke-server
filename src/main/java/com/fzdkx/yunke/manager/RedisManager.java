package com.fzdkx.yunke.manager;

import com.fzdkx.yunke.utils.JSONUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 发着呆看星
 * @create 2024/6/19
 */
@Component
public class RedisManager {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    // list取数据
    public <T> List<T> getListValue(String key, Class<T> clazz) {
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();
        // 从redis中取出数据
        List<String> range = opsForList.range(key, 0, -1);
        // 如果数据为空，返回null
        if (ObjectUtils.isEmpty(range)) {
            return null;
        }
        // 将集合中的数据，反序列化
        List<T> values = new ArrayList<>(range.size());
        range.forEach(value -> values.add(JSONUtils.toBean(value, clazz)));
        return values;
    }

    // list存数据
    public void setListValue(String key, List<?> list, long... time) {
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();
        if (ObjectUtils.isEmpty(list)) {
            return;
        }
        // 将values进行序列化
        List<String> values = new ArrayList<>(list.size());
        list.forEach(item -> values.add(JSONUtils.toJSON(item)));
        opsForList.rightPushAll(key, values);
    }

    // 删除数据
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    // 对key设置过期时间
    public void setExpireTime(String key, long timeout, TimeUnit unit) {
        stringRedisTemplate.expire(key, timeout, unit);
    }
}
