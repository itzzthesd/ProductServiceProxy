package com.example.productService.dtos;

import java.util.List;

import com.example.productService.models.SortParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    private String query;
    private int pageNumber;
    private int sizeOfPage;
    private List<SortParam> sortParamList;
}
