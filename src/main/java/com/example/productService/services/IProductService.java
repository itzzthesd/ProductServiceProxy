package com.example.productService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.productService.dtos.ProductDto;
import com.example.productService.models.Product;
import com.example.productService.security.JwtObject;

public interface IProductService {
    List<Product> getAllProducts();

     Optional<Product> getSingleProduct(Long productId);

     ResponseEntity<ProductDto> getSingleProductAuth(Long productId, JwtObject jwtObject);


     Product addNewProduct(Product product);

    Product updateProduct(Long productId, ProductDto productdto);

    void deleteProduct(Long productId);
}
