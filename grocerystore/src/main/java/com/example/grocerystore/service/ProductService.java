package com.example.grocerystore.service;

import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Long id) throws NonexistingEntityException;
    Product deleteProductById(Long id)  throws NonexistingEntityException;
    Product addProduct(Product product);
    Product updateProduct(Product product) throws NonexistingEntityException;
}
