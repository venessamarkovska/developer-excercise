package com.example.grocerystore.service;

import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Discount;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface DiscountService {
    List<Discount> getDiscounts();
    Discount getDiscountById(Long id) throws NonexistingEntityException;
    Discount getDiscountByType(String type) throws NonexistingEntityException;
    Discount deleteDiscountById(Long id)  throws NonexistingEntityException;
    Discount addDiscount(Discount discount);
    Discount updateDiscount(Discount discount) throws NonexistingEntityException;
}
