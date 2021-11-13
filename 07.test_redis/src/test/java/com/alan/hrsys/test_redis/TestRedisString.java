package com.alan.hrsys.test_redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class TestRedisString {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Test
    void setString() {
       redisTemplate.opsForValue().set("name", "Tom");
    }
    @Test
    void getString() {
        String name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
    @Test
    void updateString() {
        String name = redisTemplate.opsForValue().getAndSet("name","李静");
    }
    @Test
    void deleteString() {
        Boolean flag = redisTemplate.delete("name");
    }
}
