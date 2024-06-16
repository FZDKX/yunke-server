package com.fzdkx.yunke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fzdkx.yunke.mapper")
public class YunKeApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunKeApplication.class, args);
    }

}
