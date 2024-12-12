package com.example.productService.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.productService.dtos.ProductDto;

@SpringBootTest
public class FakeStoreProductServiceTest {

    @Autowired
    private FakeStoreProductService fk;

    @Test
    void testGetAllProducts() { // this is dependent on the 3rd party API and also our FakeStoreProductService
        // hence it is not unit test , not even integration test
        ResponseEntity<ProductDto[]> res = fk.getAllProducts();
        assertNotNull(res);
    }
}
