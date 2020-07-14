package com.example.grocerystore.service.impl;


import com.example.grocerystore.dao.PurchaseRepository;
import com.example.grocerystore.model.BillDto;
import com.example.grocerystore.model.Product;
import com.example.grocerystore.model.Purchase;
import com.example.grocerystore.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class PurchaseServiceImpl implements PurchaseService {
    private PurchaseRepository repo;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository repository) {
        this.repo = repository;
    }


    @Override
    public Purchase addPurchase(Purchase purchase) {
        return repo.save(purchase);
    }

//    @Override
//    public BillDto getBill(String discountType, List<Product> products) {
//        BillDto dto = new BillDto();
//        dto.setProducts() = getCartProducts();
//        dto.setDiscountAmount() = getDiscount();
//        dto.setDiscountAmount() = calculateDiscount();
//        dto.setTotalSum() = calculateTotalSum();
//    }

    @Override
    public List<Product> addProductToCart(Product product) {
       return null;
    }


}
