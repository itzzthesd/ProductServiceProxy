package com.example.productService.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.productService.models.Categories;
import com.example.productService.repositories.CategoryRepo;

@Service
public class CategoryService {
    private CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public Categories addNewCategory(Categories categories){
        categoryRepo.save(categories);
        return categories;
    }

    public Categories getCategory(Long id){
        return categoryRepo.findCategoriesById(id);
    }

    public List<Categories> getAllCategories(){
        return categoryRepo.findAll();
    }
}
