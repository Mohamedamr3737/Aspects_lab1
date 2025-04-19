package com.example.lab3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab3.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByOwnerId(Long userId);
}
