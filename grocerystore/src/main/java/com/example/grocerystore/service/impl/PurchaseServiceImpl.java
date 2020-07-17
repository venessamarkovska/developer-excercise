package com.example.grocerystore.service.impl;


import com.example.grocerystore.dao.PurchaseRepository;
import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Product;
import com.example.grocerystore.model.Purchase;
import com.example.grocerystore.service.DiscountService;
import com.example.grocerystore.service.ProductService;
import com.example.grocerystore.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private PurchaseRepository repo;
    private ProductService productService;
    private DiscountService discountService;
    private String DISCOUNT_3FOR2 = "3for2";
    private String DISCOUNT_SECOND_HALF_PRICE = "secondHalfPrice";


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository repository) {
        this.repo = repository;
    }


    @Override
    public Purchase addPurchase(Purchase purchase) {
        return repo.save(purchase);
    }

    @Override
    public String getBill(Long purchaseId) throws NonexistingEntityException {
        List<Product> products = getPurchaseById(purchaseId).getProducts();
        return formatSum(getTotalPrice(products));
    }

    @Override
    public int getTotalSum(Long purchaseId) throws NonexistingEntityException {
        List<Product> products = getPurchaseById(purchaseId).getProducts();
        return getTotalPrice(products);
    }

    @Override
    public Purchase updatePurchasePrice(Long purchaseId, int totalSum) throws NonexistingEntityException {
        Purchase purchase = getPurchaseById(purchaseId);
        purchase.setTotalSum(totalSum);
        return repo.save(purchase);
    }

    @Override
    public Purchase getPurchaseById(Long id) throws NonexistingEntityException {
        return repo.findById(id)
                .orElseThrow(() -> new NonexistingEntityException("Entity with ID=" + id + " does not exist."));
    }



    private int calculate3for2Discount(List<Product> products) throws NonexistingEntityException {
        return calculateDiscount(DISCOUNT_3FOR2, products);
    }

    private int calculateSecondHalfPriceDiscount(List<Product> products) throws NonexistingEntityException {
        return  calculateDiscount(DISCOUNT_SECOND_HALF_PRICE, products)/2;

    }

    private int calculateDiscount(String type, List<Product> products) throws NonexistingEntityException {
        if (discountService.getDiscountByType(type) != null) {
            int num = 0;
            if (type.equals(DISCOUNT_3FOR2)) {
                num = 3;
            }
            if (type.equals(DISCOUNT_SECOND_HALF_PRICE)) {
                num = 2;
            }
            List<Product> discountedProducts = discountService.getDiscountByType(type).getProducts();
            List<Product> productsToDiscount = new ArrayList<>();
            for (Product product : products) {
                while (productsToDiscount.size() < num && discountedProducts.contains(product)) {
                        productsToDiscount.add(product);
                        break;
                }
            }
            return (productsToDiscount.size() == num) ? retriveLowestPrice(productsToDiscount) : 0;
        } else {
            return 0;
        }
    }

    private int getTotalPrice(List<Product> products) throws NonexistingEntityException {
        int totalSum = 0;
        for(Product product : products){
            totalSum+= product.getPrice();
        }
        return totalSum-= (calculate3for2Discount(products) + calculateSecondHalfPriceDiscount(products));
    }

    private int retriveLowestPrice(List<Product> productsToDiscount) {
        int discount = 0;
        for (int i = 0; i < productsToDiscount.size() - 1; i++) {
            if (productsToDiscount.get(i).getPrice() > productsToDiscount.get(i + 1).getPrice()) {
                discount = productsToDiscount.get(i).getPrice();
            } else {
                discount = productsToDiscount.get(i + 1).getPrice();
            }
        }
        return discount;
    }

    private String formatSum(int totalSum){
        int aws=0;
        int cloud = 0;
        if(totalSum >= 100){
          aws = totalSum/100;
          cloud = totalSum % 100;
        return (aws + " aws " + cloud + " cloud.");
        } else{
            return (totalSum + "cloud");
        }
    }
}