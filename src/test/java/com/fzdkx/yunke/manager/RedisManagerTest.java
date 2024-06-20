package com.fzdkx.yunke.manager;

import com.fzdkx.yunke.bean.dao.TUser;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * @author 发着呆看星
 * @create 2024/6/19
 */
@SpringBootTest
public class RedisManagerTest {

    @Resource
    private RedisManager redisManager;

    @Test
    public void testSetListValue() {
        String key = "test:list";
        ArrayList<TUser> tUsers = new ArrayList<>();
        TUser tUser1 = new TUser();
        tUser1.setId(1);
        tUser1.setPassword("1111");
        TUser tUser2 = new TUser();
        tUser2.setId(2);
        tUser2.setPassword("2222");
        tUsers.add(tUser1);
        tUsers.add(tUser2);
        redisManager.setListValue(key, tUsers);
    }
}
