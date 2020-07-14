package com.example.grocerystore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
public class Discount {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String type;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    @ManyToMany
    @JoinTable(name = "discounts_products", joinColumns= @JoinColumn(name= "discount_id"),
            inverseJoinColumns=@JoinColumn(name = "product_id"))
    private List<Product> products;


}
