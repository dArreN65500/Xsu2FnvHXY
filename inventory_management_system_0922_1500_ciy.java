// 代码生成时间: 2025-09-22 15:00:34
package com.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryManagementSystem {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystem.class, args);
    }
}

/*
 * ProductService.java
 * Service for product management in the inventory system.
 */

package com.example.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setQuantity(productDetails.getQuantity());
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}

/*
 * ProductRepository.java
 * Repository for product data access in the inventory system.
 */

package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.inventory.model.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);
}

/*
 * Product.java
 * Model class for a product in the inventory system.
 */

package com.example.inventory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int quantity;
    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

/*
 * ProductController.java
 * REST controller for product operations in the inventory system.
 */

package com.example.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.inventory.model.Product;
import com.example.inventory.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(