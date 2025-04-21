// UserService.java
package com.example.lab4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab4.annotation.CacheRedis;
import com.example.lab4.annotation.RedisLock;
import com.example.lab4.model.User;
import com.example.lab4.repository.UserRepository;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    @CacheRedis(key = "users:all", ttl = 30)
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @RedisLock(key = "users:create", timeout = 15)
    public User createUser(String name, String email) throws InterruptedException {
        Thread.sleep(10000); // simulate long processing
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        return repo.save(u);
    }
}
