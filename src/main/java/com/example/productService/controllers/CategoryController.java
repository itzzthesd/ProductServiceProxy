package com.example.productService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productService.dtos.CategoryDto;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    @GetMapping("/{id}")
    public String getSingleCategory(@PathVariable("id") Long id){
        return "GEt" + id;
    }

    @GetMapping("")
    public String getCategory(){
        return " All cateogry.";
    }

    @PostMapping
    public String addNewCategory(@RequestBody CategoryDto categoryDto){
        return "added" + categoryDto;
    }
}
