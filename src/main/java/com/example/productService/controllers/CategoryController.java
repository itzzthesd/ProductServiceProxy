package com.example.productService.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productService.dtos.CategoryDto;
import com.example.productService.models.Categories;
import com.example.productService.services.CategoryService;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> getSingleCategory(@PathVariable("id") Long id){
        Categories tmpCat = categoryService.getCategory(id);
        ResponseEntity<Categories> res = new ResponseEntity<>(tmpCat, HttpStatus.OK);
        return res;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Categories> getSingleCategoryByName(@PathVariable("name") String name){
        Categories tmpCat = categoryService.getCategoryByName(name);
        ResponseEntity<Categories> res = new ResponseEntity<>(tmpCat, HttpStatus.OK);
        return res;
    }



    @GetMapping("")
    public ResponseEntity<List<Categories>> getCategory(){
       List<Categories> listOfCat = categoryService.getAllCategories();
       ResponseEntity<List<Categories>> res = new ResponseEntity<>(listOfCat, HttpStatus.OK);
        return res;
    }

    @PostMapping
    public ResponseEntity<Categories> addNewCategory(@RequestBody CategoryDto categoryDto){
        Categories tmpCat= DtoToCategory(categoryDto);
        Categories categories = categoryService.addNewCategory(tmpCat);
        ResponseEntity<Categories> res = new ResponseEntity<>(categories, HttpStatus.OK);
        return res;
    }

    private Categories DtoToCategory(CategoryDto categoryDto){
        Categories categories = new Categories();
        categories.setName(categoryDto.getName());
        categories.setDescription(categoryDto.getDescription());
        return categories;
    }
}
