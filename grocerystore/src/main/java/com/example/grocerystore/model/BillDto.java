package com.example.grocerystore.model;

import lombok.*;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
@NoArgsConstructor
@Setter
@Getter
public class BillDto {
    private BigDecimal totalSum;
    private List<Product> products;
    private List<Discount> discounts;
    private BigDecimal discountAmount;

}
