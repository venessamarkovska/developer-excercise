package com.example.grocerystore.service;

import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Product;

import java.util.Collection;

public interface ProductService {
    Collection<Product> getProducts();
    Product getProductById(Long id) throws NonexistingEntityException;
    Product deleteProductById(Long id)  throws NonexistingEntityException;
    Product addProduct(Product product);
    Product updateProduct(Product product) throws NonexistingEntityException;
}
