package com.example.grocerystore.service.impl;

import com.example.grocerystore.dao.DiscountRepository;
import com.example.grocerystore.exception.EntityAlreadyExistsException;
import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Discount;
import com.example.grocerystore.model.Product;
import com.example.grocerystore.service.DiscountService;
import com.example.grocerystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {
    private DiscountRepository repo;
    private ProductService productService;

    @Autowired
    public DiscountServiceImpl(DiscountRepository repository, ProductService productService) {
        this.repo = repository;
        this.productService = productService;
    }

    @Override
    public Discount addDiscount(Discount discount) throws EntityAlreadyExistsException {
        if (repo.findByType(discount.getType()) != null) {
            throw new EntityAlreadyExistsException("Entity with of type =" + discount.getType() + " already exists.");
        } else {
            List<Product> products = discount.getProducts();
            for(Product product : products){
                if(productService.getProductById(product.getId()) == null){
                    throw new NonexistingEntityException("Product with id = " + product.getId() + " does not exist.");
                }
            }
            return repo.save(discount);
        }
    }

    @Override
    public List<Discount> getDiscounts() {
        return repo.findAll();
    }

    @Override
    public Discount getDiscountById(Long id) throws NonexistingEntityException {
        return repo.findById(id)
                .orElseThrow(() -> new NonexistingEntityException("Entity with ID=" + id + " does not exist."));
    }

    @Override
    public Discount getDiscountByType(String type){
        return repo.findByType(type);
    }

    @Override
    public Discount deleteDiscountById(Long id) throws NonexistingEntityException {
        Optional<Discount> old = repo.findById(id);
        old.ifPresent(discount -> repo.deleteById(discount.getId()));
        return old
                .orElseThrow(() -> new NonexistingEntityException("Entity with ID=" + id + " does not exist."));
    }

    @Override
    public Discount updateDiscount(Discount discount) throws NonexistingEntityException {
        getDiscountById(discount.getId());
        return repo.save(discount);
    }


}
