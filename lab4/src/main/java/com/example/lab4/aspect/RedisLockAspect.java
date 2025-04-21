// RedisLockAspect.java
package com.example.lab4.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.lab4.annotation.RedisLock;
import com.example.lab4.util.RedisUtils;

@Aspect
@Component
public class RedisLockAspect {
    @Autowired private RedisUtils redis;

    @Around("@annotation(lock)")
    public Object lock(ProceedingJoinPoint pjp, RedisLock lock) throws Throwable {
        String key = lock.key();
        if (!redis.tryLock(key, lock.timeout())) throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Resource is currently locked. Try again later.");

        try {
            return pjp.proceed();
        } finally {
            redis.unlock(key);
        }
    }
}
