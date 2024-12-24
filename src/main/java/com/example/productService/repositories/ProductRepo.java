package com.example.productService.repositories;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productService.models.Product;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findByPriceBetween(Double greaterThan, Double lessThan);

    //List<Product> findByTitleEquals(String title, Pageable pageable);

    List<Product> findByTitleEquals(String query, PageRequest of);
    
} 
