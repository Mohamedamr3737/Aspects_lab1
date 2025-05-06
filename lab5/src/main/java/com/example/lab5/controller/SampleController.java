package com.example.lab5.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is public.";
    }

    @GetMapping("/secure1")
    @PreAuthorize("isAuthenticated()")
    public String secure1() {
        return "This is SECURE 1.";
    }

    @GetMapping("/secure2")
    @PreAuthorize("isAuthenticated()")
    public String secure2() {
        return "This is SECURE 2.";
    }
}