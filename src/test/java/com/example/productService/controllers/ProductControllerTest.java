package com.example.productService.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.productService.dtos.ProductDto;
import com.example.productService.models.Categories;
import com.example.productService.models.Product;
import com.example.productService.services.IProductService;

import jakarta.transaction.Transactional;

//@SpringBootTest
public class ProductControllerTest {

    //@Autowired
    private ProductController productController;

    //@MockitoBean
    private IProductService productService;

  


    //@Test
    void testDeleteSingleProduct() {

    }

    //@Test
    void testGetAllProducts() {
        
    }

    //@Test
    //@Transactional
    void testGetSingleProduct() {
       
        // create a sample Optional Product
        Product p = new Product();
        p.setId(1L);
        p.setDescription("test product");
        p.setTitle("test product");
        p.setPrice(100.00);
        Categories c = new Categories();
        c.setName("test category");
        c.setId(5L);
        p.setCategory(c);

        Optional<Product> pOptional = Optional.of(p);

        assertTrue(pOptional.isPresent(), "Product should be found");
        Product fetchedProduct = pOptional.get();
        
        when(productService.getSingleProduct(any(Long.class))).thenReturn(pOptional);

        //ResponseEntity<ProductDto> res = productController.getSingleProduct(1L);

        //assertNotNull(res);
        assertNotNull(fetchedProduct.getId(), "ID should not be null");
        assertEquals("test product", fetchedProduct.getTitle(), "Title should match");
        assertEquals(100.00, fetchedProduct.getPrice(), "Price should match");
        assertNotNull(fetchedProduct.getCategory(), "Category should not be null");
        assertEquals("test category", fetchedProduct.getCategory().getName(), "Category name should match");

    }

    //@Test
    void testGetSingleProductException() {
        
        when(productService.getSingleProduct(any(Long.class))).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> productController.getSingleProduct(1L));

    }

    //@Test
    void testPutSingleProduct() {

    }

   
}
