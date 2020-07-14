package com.example.grocerystore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "products")
@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String name;

    @Column
    private BigDecimal price;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    @ManyToMany
    @JoinTable(name = "discounts_products", joinColumns= @JoinColumn(name= "discount_id"),
            inverseJoinColumns=@JoinColumn(name = "product_id"))
    private List<Discount> discounts;
}
