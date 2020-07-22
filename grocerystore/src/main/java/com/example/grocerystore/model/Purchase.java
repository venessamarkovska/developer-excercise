package com.example.grocerystore.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Table(name = "purchase")
@Entity
@Getter
@Setter
public class Purchase {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private int totalSum;

    @ManyToMany
    @JoinTable(name = "purchase_products", joinColumns= @JoinColumn(name= "purchase_id"),
            inverseJoinColumns=@JoinColumn(name = "product_id"))
    private List<Product> products;


}
