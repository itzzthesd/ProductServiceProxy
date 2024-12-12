package com.example.productService.models;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.engine.internal.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
    private String imageUrl;
    private Boolean isPublic;
    private int numberOfUnits;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Categories category;
}
