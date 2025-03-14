package com.lab2.lab2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Advice to log the request body before any REST controller method execution
    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logBeforeController(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            // Filter out typical HttpServletRequest/Response objects
            if (arg != null && !(arg.getClass().getSimpleName().contains("Http"))) {
                logger.info("Request Body: " + arg.toString());
            }
        }
    }
}
