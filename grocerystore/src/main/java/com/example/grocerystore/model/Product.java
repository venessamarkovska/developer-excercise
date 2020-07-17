package com.example.grocerystore.model;

import lombok.*;
import javax.persistence.*;

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
    private int price;

}
