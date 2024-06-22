package com.fzdkx.yunke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
@MapperScan("com.fzdkx.yunke.mapper")
public class YunKeApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunKeApplication.class, args);
    }

    // 创建一个全局缓存
    public static final HashMap<String, Object> cacheMap = new HashMap<>();


}
