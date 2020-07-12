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
    @GeneratedValue(strategy = IDENTITY)
    @NonNull
    private Long id;

    @Column
    private String description;

    @OneToMany(mappedBy = "discount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToOne(optional = true)
    @JoinColumn(name = "purchase", nullable = false)
    private Purchase purchase;
}
