package com.example.grocerystore.dto;

import com.example.grocerystore.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiscountDto {
    private Long id;
    private String type;
    private List<Product> products;
}
