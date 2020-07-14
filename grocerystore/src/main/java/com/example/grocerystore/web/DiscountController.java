package com.example.grocerystore.web;

import com.example.grocerystore.exception.InvalidRequestException;
import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Discount;
import com.example.grocerystore.model.ErrorResponse;
import com.example.grocerystore.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/discounts")
@Slf4j
public class DiscountController {
    private DiscountService service;

    @Autowired
    public DiscountController(DiscountService service) {
        this.service = service;
    }

    @GetMapping
    List<Discount> getDiscounts() {
        return service.getDiscounts();
    }

    @GetMapping("{id}")
    Discount getDiscount(@PathVariable Long id) throws NonexistingEntityException {
        return service.getDiscountById(id);
    }

    @PostMapping
    ResponseEntity<Discount> addDiscount(@RequestBody Discount discount) {
        Discount createdDiscount = service.addDiscount(discount);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").buildAndExpand(createdDiscount.getId()).toUri();
        return ResponseEntity.created(location).body(createdDiscount);
    }

    @DeleteMapping("{id}")
    Discount deleteDiscountById(@PathVariable Long id) throws NonexistingEntityException {
        return service.deleteDiscountById(id);
    }

    @PutMapping("{id}")
    Discount updateDiscount(@PathVariable Long id, @RequestBody Discount discount)
            throws InvalidRequestException, NonexistingEntityException {
        if (!id.equals(discount.getId())) {
            throw new InvalidRequestException("IDs in path (" + id + ") and discount ("
                    + discount.getId() + ") are different.");
        }
        return service.updateDiscount(discount);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNonExistingEntityException(NonexistingEntityException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
        log.error(ex.getMessage(), ex);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("X-Custom-Header", "custom_value_1");
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
                headers,
                HttpStatus.BAD_REQUEST
        );
    }
}