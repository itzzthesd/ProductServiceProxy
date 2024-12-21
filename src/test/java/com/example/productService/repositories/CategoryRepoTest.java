package com.example.productService.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.productService.models.Categories;
import com.example.productService.models.Product;

import jakarta.transaction.Transactional;


@SpringBootTest
public class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Commit
    @Test
    void saveProductCategory(){

        // Product product = new Product();
        // product.setTitle("Laptop");
        // product.setDescription("Laptop");
        // productRepo.save(product);

        Categories categories = new Categories();
        categories.setName("car");
        categories.setDescription("car section.");
        categories.setProductList(null);
        categories.setCreatedAt(new Date());
        categories = categoryRepo.save(categories);

    
    }


    // @Test
    // @Transactional
    void getProductByPrice(){
        List<Product> plist = productRepo.findByPriceBetween(200d, 300d);

        System.out.println("Debug");
        System.out.println("Debug");
        System.out.println("Debug");
    }

    // @Test
    // @Transactional
    void testfindCategoryById(){
        Categories c = categoryRepo.findCategoriesById(2L);
        String des = c.getDescription();
        String name = c.getName();

    }

    //@Test
    void testFindByabc() {
        String x = categoryRepo.findByIdCustomQuery(2L);
        System.out.println(x);
        assertNotNull(x);
    }

    // @Test
    // @Transactional
    void testGetCategoryByName(){
        Categories cat = categoryRepo.findByName("car");
        String description = cat.getDescription();
        Long id = cat.getId();

    }

    
}
