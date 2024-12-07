package com.example.productService.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.productService.dtos.ProductDto;
import com.example.productService.models.Product;

public interface IProductService {
     ResponseEntity<ProductDto[]> getAllProducts();

     ResponseEntity<ProductDto> getSingleProduct(Long productId);

     Product addNewProduct(Product product);

    Product updateProduct(Long productId, ProductDto productdto);

    void deleteProduct(Long productId);
}
