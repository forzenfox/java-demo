package com.dylan.web.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisStringTest {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testForSetValue() {
        String key = "test";
        String value = "test-value";
        this.redisTemplate.opsForValue().set(key, value);
    }

    @Test
    public void testForGetValue() {
        Assert.assertEquals("test-value", this.redisTemplate.opsForValue().get("test"));
    }


}
