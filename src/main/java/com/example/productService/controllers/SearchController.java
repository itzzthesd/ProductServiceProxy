package com.example.productService.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productService.dtos.ProductDto;
import com.example.productService.dtos.SearchRequestDto;
import com.example.productService.models.Product;
import com.example.productService.services.SearchService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @PostMapping
    public List<ProductDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto){
        System.out.println("API got hit:");
         List<Product> result = searchService.searchProducts(searchRequestDto.getQuery(),
                                searchRequestDto.getPageNumber(), 
                                searchRequestDto.getSizeOfPage(), 
                                searchRequestDto.getSortParamList());

        List<ProductDto> shareableResult = new LinkedList<>();
        for(Product product : result) {
            shareableResult.add(getProduct(product));
        }
        return shareableResult;

    }

    private ProductDto getProduct(Product p) {
        ProductDto product = new ProductDto();
        product.setId(p.getId());
        product.setTitle(p.getTitle());
        product.setPrice(p.getPrice());
        return product;
    }
}
