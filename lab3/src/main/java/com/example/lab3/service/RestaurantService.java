package com.example.lab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab3.entity.Restaurant;
import com.example.lab3.entity.User;
import com.example.lab3.repository.RestaurantRepository;
import com.example.lab3.repository.UserRepository;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private UserRepository userRepo;

    public Restaurant addRestaurant(Long userId, String name) {
        User owner = userRepo.findById(userId).orElseThrow();
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setOwner(owner);
        return restaurantRepo.save(restaurant);
    }

    public void deleteRestaurant(Long id, Long userId) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow();
        if (!restaurant.getOwner().getId().equals(userId))
            throw new RuntimeException("Not allowed");
        restaurantRepo.delete(restaurant);
    }
}
