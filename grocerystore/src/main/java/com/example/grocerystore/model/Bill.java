package com.example.grocerystore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Component
@NoArgsConstructor
@Setter
@Getter
public class Bill {

    private BigDecimal totalValue;
    private List<Product> items;
    private List<Discount> discounts;

}
