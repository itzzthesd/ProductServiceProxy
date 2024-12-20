package com.example.productService.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productService.models.Categories;
import com.example.productService.models.Product;

import jakarta.transaction.Transactional;


@SpringBootTest
public class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    @Test
    void saveProductCAtegory(){

        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription("Laptop");
        productRepo.save(product);

        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics");
        categories.setProductList(List.of(product));
        categories = categoryRepo.save(categories);

        System.out.println("Debug");
    }


    @Test
    @Transactional
    void getProductByPrice(){
        List<Product> plist = productRepo.findByPriceBetween(200d, 300d);

        System.out.println("Debug");
        System.out.println("Debug");
        System.out.println("Debug");
    }

    @Test
    void testFindByabc() {
        String x = categoryRepo.findByIdCustomQuery(2L);
        System.out.println(x);
        assertNotNull(x);
    }
}
