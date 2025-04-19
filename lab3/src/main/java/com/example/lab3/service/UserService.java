package com.example.lab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lab3.entity.User;
import com.example.lab3.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public User signUp(String username, String password) {
        if (userRepo.findByUsername(username).isPresent())
            throw new RuntimeException("Username already exists");

        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        return userRepo.save(user);
    }

    public User login(String username, String password) {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return user;
    }
}
