package com.example.productService.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productService.models.Categories;

@Repository
public interface CategoryRepo extends JpaRepository<Categories, Long>{

    Categories save(Categories categories);

    Categories findCategoriesById(Long id);
    
    
}
