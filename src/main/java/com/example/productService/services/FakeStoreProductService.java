package com.example.productService.services;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.productService.dtos.ProductDto;
import com.example.productService.models.Categories;
import com.example.productService.models.Product;

@Service
public class FakeStoreProductService { //implements IProductService

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    //@Override
    public ResponseEntity<ProductDto[]> getAllProducts() {
         MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("auth-token", "heyaccess");
        RestTemplate restTemplate = restTemplateBuilder.build();
        
        ResponseEntity<ProductDto[]> productDto = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class, headers);
        
        return productDto;
       }

    //@Override
    public ResponseEntity<ProductDto> getSingleProduct(Long productId) {
       
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> productDto = restTemplate /// jackson is doing the mapping of response to ProductDto
                        .getForEntity("https://fakestoreapi.com/products/{productId}", ProductDto.class, productId);

        // we can convert product DTO to product and return product to controller as well 
        
        return productDto;
    }

    //@Override
    public Product addNewProduct(ProductDto productdto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/products", productdto, ProductDto.class);
        Product product = getProduct(productdto);
        return product;
    }

    //@Override
    public Product updateProduct(Long productId, ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put("https://fakestoreapi.com/products/{productId}", productDto, productId); // returns void 
        Product product = getProduct(productDto);
       return product;
    }

    //@Override
    public void deleteProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{productId}", productId);
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
