// src/main/java/com/lab2/lab2/repository/ProductRepository.java
package com.lab2.lab2.repository;

import com.lab2.lab2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
