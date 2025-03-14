package com.example.lab1.demo.aspect;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspect {

    private static final String DUMMY_TOKEN = "Bearer dummy-token-12345"; // Dummy Token

    private final HttpServletRequest request;

    public AuthorizationAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Around("execution(* com.example.lab1.demo.controller.OrderController.*(..))")
    public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.equals(DUMMY_TOKEN)) {
            System.out.println("Unauthorized Message From Aspect Service");
            return ResponseEntity.status(401).body(Map.of("message","Unauthorized: Invalid or missing Bearer token"));
        }

        System.out.println("Authorization Success Message From Aspect Service");
        return joinPoint.proceed();
    }
}
