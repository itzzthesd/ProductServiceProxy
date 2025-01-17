package com.example.productService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.productService.dtos.ProductDto;
import com.example.productService.models.Categories;
import com.example.productService.models.Product;
import com.example.productService.security.JwtObject;

@Service
public class FakeStoreProductService implements IProductService{ //

    private RestTemplateBuilder restTemplateBuilder;

    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder, 
                                    RedisTemplate<String, Object> redisTemplate){
        this.restTemplateBuilder = restTemplateBuilder;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public ResponseEntity<ProductDto[]> getAllProducts() {
         MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("auth-token", "heyaccess");
        RestTemplate restTemplate = restTemplateBuilder.build();
        
        ResponseEntity<ProductDto[]> productDto = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class, headers);
        
        return productDto;
       }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
       ProductDto cachedDto = (ProductDto)redisTemplate.opsForHash().get("PRODUCTS", productId);
       if(cachedDto == null){
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<ProductDto> productDto = restTemplate /// jackson is doing the mapping of response to ProductDto
                            .getForEntity("https://fakestoreapi.com/products/{productId}", ProductDto.class, productId);

            // put into the redis server
            redisTemplate.opsForHash().put("PRODUCTS", productId, productDto.getBody());

            // we can convert product DTO to product and return product to controller as well 
            Product product = getProduct(productDto.getBody());
            Optional<Product> opProd = Optional.of(product);

            
            return opProd;
       }else{
            Optional<Product> opProd = Optional.of(getProduct(cachedDto));
            return opProd;
       }
        
        
    }

    @Override
    public Product addNewProduct(ProductDto productdto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/products", productdto, ProductDto.class);
        Product product = getProduct(productdto);
        return product;
    }

    @Override
    public Product updateProduct(Long productId, ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put("https://fakestoreapi.com/products/{productId}", productDto, productId); // returns void 
        Product product = getProduct(productDto);
       return product;
    }

    @Override
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
    @Override
    public ResponseEntity<ProductDto> getSingleProductAuth(Long productId, JwtObject jwtObject) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSingleProductAuth'");
    }
    
}
