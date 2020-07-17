package com.example.grocerystore.model;


import lombok.*;
import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "discounts_products", joinColumns= @JoinColumn(name= "discount_id"),
            inverseJoinColumns=@JoinColumn(name = "product_id"))
    private List<Product> products;


}
