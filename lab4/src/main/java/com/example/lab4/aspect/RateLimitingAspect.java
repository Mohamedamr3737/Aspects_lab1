// RateLimitingAspect.java
package com.example.lab4.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.lab4.annotation.RateLimit;
import com.example.lab4.util.RedisUtils;

@Aspect
@Component
public class RateLimitingAspect {
    @Autowired private RedisUtils redis;

    @Around("@annotation(rateLimit)")
    public Object limit(ProceedingJoinPoint pjp, RateLimit rateLimit) throws Throwable {
        System.out.println("🚨 RateLimitingAspect triggered for: " + rateLimit.key());

        boolean allowed = redis.incrementRate(rateLimit.key(), rateLimit.limit(), rateLimit.timeout());

        System.out.println("⏳ Allowed? " + allowed);

        if (!allowed) {
            throw new RuntimeException("Rate limit exceeded.");
        }
        return pjp.proceed();
    }


}
