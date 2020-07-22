package com.example.grocerystore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private int price;
}
