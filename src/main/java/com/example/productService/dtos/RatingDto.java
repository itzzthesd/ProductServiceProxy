package com.example.productService.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatingDto implements Serializable {
    private String rate;
    private String count;
}
