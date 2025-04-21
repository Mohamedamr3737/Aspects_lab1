// RedisUtils.java
package com.example.lab4.util;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {
    @Autowired private RedisTemplate<String, Object> redis;

    public boolean tryLock(String key, int timeout) {
        Boolean success = redis.opsForValue().setIfAbsent(key, "LOCKED", Duration.ofSeconds(timeout));
        return Boolean.TRUE.equals(success);
    }

    public void unlock(String key) {
        redis.delete(key);
    }

    public boolean incrementRate(String key, int limit, int timeout) {
        Long count = redis.opsForValue().increment(key);

        if (count == 1L) {
            redis.expire(key, Duration.ofSeconds(timeout));
        }

        System.out.println("🔁 Redis [" + key + "] Count: " + count + " / Limit: " + limit);

        return count != null && count <= limit;
    }



    public Object get(String key) {
        return redis.opsForValue().get(key);
    }

    public void set(String key, Object value, int ttl) {
        redis.opsForValue().set(key, value, Duration.ofSeconds(ttl));
    }
}
