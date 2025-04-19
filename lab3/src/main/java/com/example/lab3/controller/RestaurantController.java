package com.example.lab3.controller;

import com.example.lab3.entity.Restaurant;
import com.example.lab3.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/add")
    public ResponseEntity<Restaurant> addRestaurant(@RequestParam Long userId, @RequestParam String name) {
        return ResponseEntity.ok(restaurantService.addRestaurant(userId, name));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id, @RequestParam Long userId) {
        restaurantService.deleteRestaurant(id, userId);
        return ResponseEntity.ok("Restaurant deleted");
    }
}
