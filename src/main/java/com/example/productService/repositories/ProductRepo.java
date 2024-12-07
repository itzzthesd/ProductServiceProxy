package com.example.productService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productService.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

    Product save(Product product);
    
} 
