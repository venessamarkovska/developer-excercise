package com.example.grocerystore.service;

import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Product;
import com.example.grocerystore.model.Purchase;
import java.util.List;


public interface PurchaseService {
    Purchase addPurchase(Purchase purchase);
    Purchase getPurchaseById(Long id) throws NonexistingEntityException;
    int getBill(Long purchaseId) throws NonexistingEntityException;
}
