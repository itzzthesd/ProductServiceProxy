package com.example.productService.models;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
//    @ManyToOne
//     private Categories category;
    private String imageUrl;
    private Boolean isPublic;
    private int numberOfUnits;
    @ManyToOne
    private Categories category;
}
