package com.fzdkx.yunke.utils;

import org.springframework.util.ObjectUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author 发着呆看星
 * @create 2024/6/19
 * 缓存工具
 */
public class CacheUtils {

    /**
     * 带有缓存的查询功能
     *
     * @param cacheSelector    生产者：从缓存中获取数据
     * @param databaseSelector 生成者：从数据库中获取数据
     * @param cacheSave        消费者：将数据库中的数据进行消费(保存到缓存中)
     * @return 返回需要查询的数据
     */
    public static <T> T getCacheData(Supplier<T> cacheSelector, Supplier<T> databaseSelector, Consumer<T> cacheSave) {
        // 1）先从redis中查询数据
        T data = cacheSelector.get();
        // 判断数据是否在缓存中查到
        if (ObjectUtils.isEmpty(data)) {
            // 如果redis中没有数据，那么从数据库中查询
            data = databaseSelector.get();
            // 判断数据库中是否存在数据
            if (!ObjectUtils.isEmpty(data)) {
                // 如果数据库中有数据，那么将数据放入redis(消费者消费)
                cacheSave.accept(data);
            }
        }
        return data;
    }
}
