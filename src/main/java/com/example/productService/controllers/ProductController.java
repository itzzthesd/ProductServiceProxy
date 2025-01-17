package com.example.productService.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productService.clients.notificationService.OrderServiceClient;
import com.example.productService.clients.notificationService.ProductRequestDto;
import com.example.productService.dtos.ProductDto;
import com.example.productService.models.Categories;
import com.example.productService.models.Product;
import com.example.productService.security.JwtObject;
import com.example.productService.security.TokenValidator;
import com.example.productService.services.IProductService;
import com.example.productService.services.SelfProductService;

import jakarta.annotation.Nullable;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/products")
public class ProductController {
    
    IProductService productService;

    @Autowired
    private CategoryController categoryController;

    private TokenValidator tokenValidator;

    private final OrderServiceClient orderServiceClient;

    public ProductController(IProductService productService, TokenValidator tokenValidator, 
                            OrderServiceClient orderServiceClient){
        this.productService = productService;
        this.tokenValidator = tokenValidator;
        this.orderServiceClient = orderServiceClient;
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId){
        try{
            if(productId<0){
                throw new IllegalArgumentException("productId should be greater than 0");
            }
            
            Optional<Product> productOptional = productService.getSingleProduct(productId);
            Product product = productOptional.get();
            ProductDto productDto = ProductToDto(product);
            // return response entity
            //ResponseEntity<ProductDto> responseEntity = new ResponseEntity<>(productDto, headers, HttpStatus.OK);

           

            // call the info to notification service.. that one of the product has been called
            ProductRequestDto pDto = new ProductRequestDto();
            pDto.setName(product.getTitle());
            pDto.setPrice(product.getPrice());
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            try {
                ResponseEntity<ProductRequestDto> orderServiceResponse = orderServiceClient.getProduct(pDto);
                headers.add("Accept", "application/json");
                headers.add("auth-token", "heyaccess");
                headers.add("notification-service-response", orderServiceResponse.getBody().toString());
             
            } catch (Exception e) {
                // TODO: handle exception
            }
            
            
             ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, headers, HttpStatus.OK);

            return responseEntity;
        }catch(Exception e){
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            throw e; 
        }
                
        
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<ProductDto> getSingleProduct(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
    //                         @PathVariable("id") Long productId){
    //     try{
    //         JwtObject authTokenObj = null;
    //         if(authToken != null){
    //             Optional<JwtObject> authObjectOptional = tokenValidator.validateToken(authToken);
    //             if(authObjectOptional.isEmpty()){
    //                 // throw an exception
    //             }
    //             authTokenObj = authObjectOptional.get();
    //         }
    //         if(productId>10){
    //             throw new IllegalArgumentException("productId should be less than 10");
    //         }
    //         MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    //         headers.add("Accept", "application/json");
    //         headers.add("auth-token", "heyaccess");
    //         ProductDto productDto = productService.getSingleProduct(productId).getBody();
    //         // with auth
    //         //ProductDto productDtoAuth = productService.getSingleProductAuth(productId, authTokenObj).getBody();
            
    //         ResponseEntity<ProductDto> responseEntity = new ResponseEntity<>(productDto, headers, HttpStatus.OK);

    //         return responseEntity;
    //     }catch(Exception e){
    //         //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //         throw e; 
    //     }
                
        
    // }

    @GetMapping("")
    public ResponseEntity<ProductDto[]> getAllProducts(){
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("auth-token", "heyaccess");
        //List<Product> allPRoduct = productService.getAllProducts();
        ResponseEntity<ProductDto[]> listOfProdDtoEntity = productService.getAllProducts();
        // ProductDto[] listOfProductDtos = new ProductDto[allPRoduct.size()];
        // int i =0;
        // for(Product product: allPRoduct){
        //     ProductDto productDto = ProductToDto(product);
        //     listOfProductDtos[i] = productDto;
        //     i++;
        // }
        
        // // calling the order service 
        // ProductRequestDto pDto = new ProductRequestDto();
        //     pDto.setName("all product");
        //     pDto.setPrice(100.00);
        // ResponseEntity<ProductRequestDto> notificationResponse = orderServiceClient.getProduct(pDto);

        // MultiValueMap<String, String> headers2 = new LinkedMultiValueMap<>();
        // headers2.add("Accept", "application/json");
        // headers2.add("auth-token", "heyaccess");
        // headers2.add("notification-service-response", notificationResponse.getHeaders().toString());
        
        ResponseEntity<ProductDto[]> response = new ResponseEntity<ProductDto[]>(listOfProdDtoEntity.getBody(), headers, HttpStatus.OK);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> putSingleProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto){
        Product product = productService.updateProduct(productId, productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    // @PostMapping
    // public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){
    //     Product tmpProd = DtoToProduct(productDto);
    //     Product product = productService.addNewProduct(tmpProd);
    //     return new ResponseEntity<>(product, HttpStatus.OK);

    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSingleProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    // @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    // public ResponseEntity<String> handleException(Exception e){
    //     return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    private Product DtoToProduct(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(new Date());
        //Categories categories = new Categories();
        
        Categories categories2 = categoryController.getSingleCategoryByName(productDto.getCategory()).getBody();
        product.setCategory(categories2);
        //categories.setDescription(null);
        //product.setCategory(categories);
        return product;
    }

    private ProductDto ProductToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCreatedAt(product.getCreatedAt());
        //productDto.setImageUrl(product.getImage());
        productDto.setDescription(product.getDescription());
        Categories categories = new Categories();
        if(product.getCategory()==null){
            productDto.setCategory(null);
            return productDto;
        }
        categories.setDescription(product.getCategory().getDescription());
        categories.setName(product.getCategory().getName());
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }
}
