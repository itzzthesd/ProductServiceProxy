package com.example.productService.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.example.productService.models.Categories;
import com.example.productService.models.Product;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;
    
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    void getProducctTest(){
        Optional<Product> product = productRepo.findById(154L);
        Product p = product.get();
        System.out.println("debug");
        //return p;

    }

    @Test
    @Commit
    void testAddNewProduct(){
        Product product = new Product();
        //product.setId(303L);
        product.setTitle("lambo");
        product.setDescription("fastest car.");
        product.setCreatedAt(new Date());
        product.setIsPublic(true);
        productRepo.save(product); // first save the product
        //Categories categorie = categoryRepo.findCategoriesById(1L);
        Categories categorie2 = categoryRepo.findByName("car");
        String x = categorie2.getDescription();
        product.setCategory(categorie2);
        productRepo.save(product); // then set the category as well 
        
    }

    @Test
    @Transactional
    void testGetAllProducts(){
        List<Product> listOfProduct = productRepo.findAll();
        for(Product p: listOfProduct){
            String name = p.getTitle();
            double price = p.getPrice();
        }
        
    }

    @Test
    @Transactional
    void testSearchProductRepo(){
        Sort sort;
        sort = Sort.by("price");
        int pageNumber = 0;
        int sizeOfPage = 2;
       
        List<Product> listOfProduct = productRepo.findByTitleEquals("samsung", PageRequest.of(pageNumber, sizeOfPage, sort));
        System.out.println(listOfProduct.size());
        for(Product p: listOfProduct){
            String title = p.getTitle();
            double price = p.getPrice();
            System.out.println(p.getTitle());
        }
    }
}
