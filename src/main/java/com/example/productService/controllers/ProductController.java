package com.example.productService.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productService.dtos.ProductDto;
import com.example.productService.models.Categories;
import com.example.productService.models.Product;
import com.example.productService.services.IProductService;
import com.example.productService.services.SelfProductService;

import java.util.List;
import java.util.Locale.Category;

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
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/products")
public class ProductController {
    
    IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("id") Long productId){
        try{
            if(productId>10){
                throw new IllegalArgumentException("productId should be less than 10");
            }
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("auth-token", "heyaccess");
            ProductDto productDto = productService.getSingleProduct(productId).getBody();
            ResponseEntity<ProductDto> responseEntity = new ResponseEntity<>(productDto, headers, HttpStatus.OK);

            return responseEntity;
        }catch(Exception e){
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            throw e; 
        }
                
        
    }

    @GetMapping("")
    public ResponseEntity<ProductDto[]> getAllProducts(){
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("auth-token", "heyaccess");
        ResponseEntity<ProductDto[]> allPRoduct = productService.getAllProducts();
        //ResponseEntity<ProductDto[]> response = new ResponseEntity<ProductDto[]>(allPRoduct, headers);
        return allPRoduct;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> putSingleProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto){
        Product product = productService.updateProduct(productId, productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){
        Product tmpProd = DtoToProduct(productDto);
        Product product = productService.addNewProduct(tmpProd);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

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
        Categories categories = new Categories();
        categories.setDescription(null);
        product.setCategory(categories);
        System.out.println(product);
        System.out.println(categories);
        return product;
    }
}
