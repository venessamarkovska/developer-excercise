package com.example.grocerystore.service;

import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Purchase;


public interface PurchaseService {
    Purchase addPurchase(Purchase purchase);
    Purchase getPurchaseById(Long id) throws NonexistingEntityException;
    String getBill(Long purchaseId) throws NonexistingEntityException;
    int getTotalSum(Long purchaseId) throws NonexistingEntityException;
    Purchase updatePurchasePrice(Long purchaseId, int totalSum) throws NonexistingEntityException;
}
