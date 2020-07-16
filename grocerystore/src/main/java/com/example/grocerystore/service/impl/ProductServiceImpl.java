package com.example.grocerystore.service.impl;

import com.example.grocerystore.dao.ProductRepository;
import com.example.grocerystore.exception.EntityAlreadyExistsException;
import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Product;
import com.example.grocerystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository repo;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repo = repository;
    }

    @Override
    public Product addProduct(Product product) throws EntityAlreadyExistsException {
        if (repo.findByName(product.getName()) != null) {
            throw new EntityAlreadyExistsException("Entity with this name =" + product.getName() + " already exists.");
        } else {
            return repo.save(product);
        }
    }

    @Override
    public List<Product> getProducts() {
        return repo.findAll();
    }

    @Override
    public Product getProductById(Long id) throws NonexistingEntityException {
        return repo.findById(id)
                .orElseThrow(() -> new NonexistingEntityException("Entity with ID=" + id + " does not exist."));
    }

    @Override
    public Product deleteProductById(Long id) throws NonexistingEntityException {
        Optional<Product> old = repo.findById(id);
        old.ifPresent(product -> repo.deleteById(product.getId()));
        return old
                .orElseThrow(() -> new NonexistingEntityException("Entity with ID=" + id + " does not exist."));
    }

    @Override
    public Product updateProduct(Product product) throws NonexistingEntityException {
        getProductById(product.getId());
        return repo.save(product);
    }

    @Override
    public Product getProductByName(String name) throws NonexistingEntityException {
        if (name != null && repo.findByName(name) != null) {
            return repo.findByName(name);
        } else {
            throw new NonexistingEntityException("Entity with name=" + name + " does not exist.");
        }

    }
}