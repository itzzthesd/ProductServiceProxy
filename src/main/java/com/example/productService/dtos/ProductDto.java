package com.example.productService.dtos;

import java.io.Serializable;
import java.util.Date;

import com.example.productService.models.Categories;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto implements Serializable { /// always expose our product Dto to clients, never expose 3rd party DTo to clients
    // serializable is due to redis implementation
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    private RatingDto rating;
    private Date createdAt;
}
