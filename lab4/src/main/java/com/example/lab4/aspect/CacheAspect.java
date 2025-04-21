// CacheAspect.java
package com.example.lab4.aspect;

import com.example.lab4.annotation.CacheRedis;
import com.example.lab4.util.RedisUtils;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component
public class CacheAspect {
    @Autowired private RedisUtils redis;

    @Around("@annotation(cache)")
    public Object cache(ProceedingJoinPoint pjp, CacheRedis cache) throws Throwable {
        Object data = redis.get(cache.key());
        if (data != null) return data;

        Object result = pjp.proceed();
        redis.set(cache.key(), result, cache.ttl());
        return result;
    }
}
