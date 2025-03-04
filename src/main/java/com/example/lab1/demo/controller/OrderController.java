package com.example.lab1.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final List<Map<String, Object>> orders = new ArrayList<>();
    private static int currentOrderId = 1; // Auto-incrementing order ID

    static {
        orders.add(new HashMap<>(Map.of("orderId", currentOrderId++, "userId", 101, "totalAmount", 150.0, "status", "Delivered")));
        orders.add(new HashMap<>(Map.of("orderId", currentOrderId++, "userId", 102, "totalAmount", 200.0, "status", "Pending")));
        orders.add(new HashMap<>(Map.of("orderId", currentOrderId++, "userId", 101, "totalAmount", 50.0, "status", "Shipped")));
        orders.add(new HashMap<>(Map.of("orderId", currentOrderId++, "userId", 103, "totalAmount", 300.0, "status", "Delivered")));
    }

    //Create a new order
    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Map<String, Object> orderData) {
        orderData.put("orderId", currentOrderId++);
        orders.add(orderData);
        return ResponseEntity.ok(Map.of("message", "Order created successfully", "order", orderData));
    }

    //Get all orders
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllOrders() {
        return ResponseEntity.ok(orders);
    }

    //Get order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable int orderId) {
        return orders.stream()
                .filter(order -> (int) order.get("orderId") == orderId)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(Map.of("message", "Order not found")));
    }

    //Get orders for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getUserOrders(@PathVariable int userId) {
        List<Map<String, Object>> userOrders = new ArrayList<>();
        for (Map<String, Object> order : orders) {
            if ((int) order.get("userId") == userId) {
                userOrders.add(order);
            }
        }

        return userOrders.isEmpty()
                ? ResponseEntity.status(404).body(List.of(Map.of("message", "No orders found for this user")))
                : ResponseEntity.ok(userOrders);
    }

    //Update an order by ID
    @PutMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> updateOrder(@PathVariable int orderId, @RequestBody Map<String, Object> updatedData) {
        for (Map<String, Object> order : orders) {
            if ((int) order.get("orderId") == orderId) {
                order.putAll(updatedData);
                return ResponseEntity.ok(Map.of("message", "Order updated successfully", "order", order));
            }
        }
        return ResponseEntity.status(404).body(Map.of("message", "Order not found"));
    }

    //Delete an order by ID
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> deleteOrder(@PathVariable int orderId) {
        Iterator<Map<String, Object>> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> order = iterator.next();
            if ((int) order.get("orderId") == orderId) {
                iterator.remove();
                return ResponseEntity.ok(Map.of("message", "Order deleted successfully"));
            }
        }
        return ResponseEntity.status(404).body(Map.of("message", "Order not found"));
    }
}
