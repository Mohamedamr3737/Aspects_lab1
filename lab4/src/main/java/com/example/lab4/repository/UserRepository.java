// UserRepository.java
package com.example.lab4.repository;

import com.example.lab4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
