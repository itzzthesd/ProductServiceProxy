package com.example.productService.models;

import java.util.List;

// import jakarta.persistence.Entity;
// import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
public class Categories extends BaseModel{
    private String name;
    private String description;
    
    //@OneToMany
    private List<Product> productList;
    
}
