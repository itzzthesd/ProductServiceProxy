package com.example.productService.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productService.models.Product;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;
    
    @Test
    @Transactional
    void getProducctTest(){
        Optional<Product> product = productRepo.findById(154L);
        Product p = product.get();
        System.out.println("debug");
        //return p;

    }
}
