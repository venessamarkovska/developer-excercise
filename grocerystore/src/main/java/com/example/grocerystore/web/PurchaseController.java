package com.example.grocerystore.web;

import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Purchase;
import com.example.grocerystore.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/purchase")
@Slf4j
public class PurchaseController {
    @Autowired
    private PurchaseService service;

    @PostMapping
    ResponseEntity<Purchase> addPurchase(@RequestBody Purchase purchase) {
        Purchase createdPurchase = service.addPurchase(purchase);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").buildAndExpand(createdPurchase.getId()).toUri();
        return ResponseEntity.created(location).body(createdPurchase);
    }

    @GetMapping("/bill/{id}")
    public String getBill(@PathVariable Long id) throws NonexistingEntityException {
        int totalSum = service.getTotalSum(id);
        service.updatePurchasePrice(id,totalSum);
        return service.getBill(id);
    }
}
