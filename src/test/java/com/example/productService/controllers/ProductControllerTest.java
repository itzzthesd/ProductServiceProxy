package com.example.productService.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.productService.dtos.ProductDto;
import com.example.productService.models.Product;
import com.example.productService.services.IProductService;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

   @MockitoBean
    private IProductService productService;

    @Test
    void testAddNewProduct() {

    }

    @Test
    void testDeleteSingleProduct() {

    }

    @Test
    void testGetAllProducts() {
        
    }

    @Test
    void testGetSingleProduct() {
        // Create a sample ProductDto object
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setDescription("mock");
        ResponseEntity<ProductDto> responseEntity = ResponseEntity.ok(productDto);
        when(productService.getSingleProduct(any(Long.class))).thenReturn(responseEntity);

        ResponseEntity<ProductDto> res = productController.getSingleProduct(1L);

        assertNotNull(res);

        }

        @Test
    void testGetSingleProductException() {
        
        when(productService.getSingleProduct(any(Long.class))).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> productController.getSingleProduct(1L));

        }

    @Test
    void testPutSingleProduct() {

    }
}
