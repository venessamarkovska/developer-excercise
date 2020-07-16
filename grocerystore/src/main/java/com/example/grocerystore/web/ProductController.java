package com.example.grocerystore.web;


import com.example.grocerystore.exception.EntityAlreadyExistsException;
import com.example.grocerystore.exception.InvalidRequestException;
import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.ErrorResponse;
import com.example.grocerystore.model.Product;
import com.example.grocerystore.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("{id}")
    Product getProduct(@PathVariable Long id) throws NonexistingEntityException {
        return service.getProductById(id);
    }

    @PostMapping
    ResponseEntity<Product> addProduct(@RequestBody Product product) throws EntityAlreadyExistsException {
        Product createdProduct = service.addProduct(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").buildAndExpand(createdProduct.getId()).toUri();
        return ResponseEntity.created(location).body(createdProduct);
    }

    @DeleteMapping("{id}")
    Product deleteProductById(@PathVariable Long id) throws NonexistingEntityException {
        return service.deleteProductById(id);
    }

    @PutMapping("{id}")
    Product updateProduct(@PathVariable Long id, @RequestBody Product product)
            throws InvalidRequestException, NonexistingEntityException{
        if( !id.equals(product.getId()) ) {
            throw new InvalidRequestException("IDs in path (" + id + ") and product ("
                    + product.getId() + ") are different.");
        }
        return service.updateProduct(product);
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

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
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
