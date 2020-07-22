package com.example.grocerystore.web;


import com.example.grocerystore.dto.ProductDto;
import com.example.grocerystore.exception.EntityAlreadyExistsException;
import com.example.grocerystore.exception.InvalidRequestException;
import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Product;
import com.example.grocerystore.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {
    private ProductService service;
    private ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    List<ProductDto> getProducts() {
        List<Product> products = service.getProducts();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    ProductDto getProduct(@PathVariable Long id) throws NonexistingEntityException {
        return convertToDto(service.getProductById(id));
    }

    @PostMapping
    ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) throws EntityAlreadyExistsException {
        Product product = convertToEntity(productDto);
        Product createdProduct = service.addProduct(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").buildAndExpand(createdProduct.getId()).toUri();
        return ResponseEntity.created(location).body(convertToDto(createdProduct));
    }

    @DeleteMapping("{id}")
    ResponseEntity<String> deleteProductById(@PathVariable Long id) throws NonexistingEntityException {
        service.deleteProductById(id);
        return new ResponseEntity<String>("Product Deleted", HttpStatus.OK);
    }

    @PutMapping("{id}")
    ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto)
            throws InvalidRequestException, NonexistingEntityException{
        Product product = convertToEntity(productDto);
        if( !id.equals(product.getId()) ) {
            throw new InvalidRequestException("IDs in path (" + id + ") and product ("
                    + product.getId() + ") are different.");
        }
        return convertToDto(service.updateProduct(product));
    }

    private ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
