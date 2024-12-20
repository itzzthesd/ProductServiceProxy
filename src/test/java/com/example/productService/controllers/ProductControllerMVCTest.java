package com.example.productService.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.productService.dtos.ProductDto;
import com.example.productService.models.Categories;
import com.example.productService.models.Product;
import com.example.productService.services.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    IProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testGetAllProducts() throws JsonProcessingException, Exception{

        // first set of products dto
        ProductDto products[] = new ProductDto[3];
       products[0] = new ProductDto();
       products[1] = new ProductDto();
       products[2] = new ProductDto();
       ResponseEntity<ProductDto[]> rs = ResponseEntity.ok(products);

       // second set of products dto 
       ProductDto products1[] = new ProductDto[2];
       products1[0] = new ProductDto();
       products1[1] = new ProductDto();
       ResponseEntity<ProductDto[]> rs1 = ResponseEntity.ok(products1);

       //create list of product
       List<Product> lisProduct = new ArrayList<>();
       lisProduct.add ( new Product());
       lisProduct.add ( new Product());
       lisProduct.add ( new Product());


       // whenever service is called it will return the "rs" : first data set
       when(productService.getAllProducts()).thenReturn(lisProduct);

       // and calling the API and checking it with "rs"
       mockMvc.perform(get("/products"))
               .andExpect(status().isOk())
               .andExpect(content().string(objectMapper.writeValueAsString(products)));
   
    }

       @Test
   void createProduct() throws Exception {
       Product productToCreate = new Product();
       productToCreate.setTitle("iPhone 15 Pro Max");
       productToCreate.setImageUrl("some image");
       productToCreate.setDescription("Best iPhone Ever");

       Product expectedProduct = new Product();
       expectedProduct.setId(1L);
       expectedProduct.setTitle("iPhone 15 Pro Max");
       expectedProduct.setImageUrl("some image");
       expectedProduct.setDescription("Best iPhone Ever");

       when(productService.addNewProduct(any(Product.class))).thenReturn(expectedProduct);

       mockMvc.perform(
               post("/products")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(productToCreate)))
               .andExpect(status().isOk())
               .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)));
             //  .andExpect(jsonPath("$.student.name", is("Nikhil")));
            //    .andExpect(jsonPath("$.length()", is(2)));

   }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        return product;
    }

}
