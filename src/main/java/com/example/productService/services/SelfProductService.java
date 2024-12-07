package com.example.productService.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.productService.dtos.ProductDto;
import com.example.productService.models.Product;
import com.example.productService.repositories.ProductRepo;

@Service
public class SelfProductService implements IProductService{

   ProductRepo productRepo;

   public SelfProductService(ProductRepo productRepo){
      this.productRepo = productRepo;
   }


   @Override
   public ResponseEntity<ProductDto[]> getAllProducts() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
   }

   @Override
   public ResponseEntity<ProductDto> getSingleProduct(Long productId) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getSingleProduct'");
   }

   @Override
   public Product addNewProduct(Product product) {
          productRepo.save(product);
          return product;
   }

   @Override
   public Product updateProduct(Long productId, ProductDto productdto) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
   }

   @Override
   public void deleteProduct(Long productId) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
   }

    
   
    
}
