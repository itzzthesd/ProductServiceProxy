package com.example.productService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private Categories category;
    private String imageUrl;
    // private Boolean isPublic;
    // private int numberOfUnits;
}
