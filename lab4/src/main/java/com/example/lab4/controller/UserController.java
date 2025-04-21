// UserController.java
package com.example.lab4.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab4.annotation.RateLimit;
import com.example.lab4.model.User;
import com.example.lab4.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired private UserService service;

    @GetMapping
    @RateLimit(key = "users:get", limit = 5, timeout = 10)
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody Map<String, String> body) throws InterruptedException {
        return service.createUser(body.get("name"), body.get("email"));
    }
}
