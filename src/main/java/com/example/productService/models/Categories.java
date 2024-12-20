package com.example.productService.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
// import jakarta.persistence.Entity;
// import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Categories extends BaseModel{
    private String name;
    private String description;
    
    @OneToMany(mappedBy = "category")
    //@JsonIgnore
    private List<Product> productList;

    @Override
    public String toString(){
        return this.getName() + ", " + this.getDescription();
    }
    
}
