package com.example.grocerystore.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @NonNull
    private Long id;

    @Column
    private BigDecimal totalSum;
    //List<Customer> customers;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;
}
