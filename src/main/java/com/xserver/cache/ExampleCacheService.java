package com.xserver.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExampleCacheService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public String getString() {
        redisTemplate.opsForValue().set("redisTemplate", "OK");
        return redisTemplate.opsForValue().get("redisTemplate");
    }
}
