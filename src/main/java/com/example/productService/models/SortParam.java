package com.example.productService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
    private String paramName;
    private String sortType; // ASC or DESC
}

