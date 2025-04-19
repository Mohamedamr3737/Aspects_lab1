package com.example.lab3.controller;

import com.example.lab3.entity.User;
import com.example.lab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody Map<String, String> payload) {
        return ResponseEntity.ok(userService.signUp(payload.get("username"), payload.get("password")));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> payload) {
        return ResponseEntity.ok(userService.login(payload.get("username"), payload.get("password")));
    }
}
