package com.example.productService.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductResponseDto {
     private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private CategoryDto category;
    private RatingDto rating;
    private Date createdAt;
}
