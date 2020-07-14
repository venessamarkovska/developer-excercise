package com.example.grocerystore.service;

import com.example.grocerystore.model.BillDto;
import com.example.grocerystore.model.Product;
import com.example.grocerystore.model.Purchase;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PurchaseService {
    Purchase addPurchase(Purchase purchase);
//    BillDto getBill(String discountType, List<Product> products);
    List<Product> addProductToCart(Product product);
}
