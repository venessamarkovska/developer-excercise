package com.example.grocerystore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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

    @NonNull
    @Column(unique=true, nullable=false)
    private String name;

    @Column
    private BigDecimal price;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    @ManyToOne(optional = true)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne(optional = true)
    @JoinColumn(name = "discount_id", nullable = false)
    private Discount discount;

    public Long getId() {
        return id;
    }
}
