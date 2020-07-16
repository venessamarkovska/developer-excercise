package com.example.grocerystore.service;

import com.example.grocerystore.exception.EntityAlreadyExistsException;
import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Product;
import java.util.List;


public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Long id) throws NonexistingEntityException;
    Product deleteProductById(Long id)  throws NonexistingEntityException;
    Product addProduct(Product product) throws EntityAlreadyExistsException;
    Product updateProduct(Product product) throws NonexistingEntityException;
    Product getProductByName(String name) throws NonexistingEntityException;
}
