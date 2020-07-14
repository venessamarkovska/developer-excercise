package com.example.grocerystore.dao;

import com.example.grocerystore.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount findByType(String type);
}
