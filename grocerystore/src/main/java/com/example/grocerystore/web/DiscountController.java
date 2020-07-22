package com.example.grocerystore.web;

import com.example.grocerystore.dto.DiscountDto;
import com.example.grocerystore.exception.EntityAlreadyExistsException;
import com.example.grocerystore.exception.InvalidRequestException;
import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.Discount;
import com.example.grocerystore.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/discounts")
@Slf4j
public class DiscountController {
    private DiscountService service;
    private ModelMapper modelMapper;

    @Autowired
    public DiscountController(DiscountService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    List<DiscountDto> getDiscounts() {
        List<Discount> discounts = service.getDiscounts();
        return discounts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    DiscountDto getDiscount(@PathVariable Long id) throws NonexistingEntityException {
        return convertToDto(service.getDiscountById(id));
    }

    @PostMapping
    ResponseEntity<DiscountDto> addDiscount(@RequestBody DiscountDto discountDto) throws EntityAlreadyExistsException {
        Discount discount = convertToEntity(discountDto);
        Discount createdDiscount = service.addDiscount(discount);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").buildAndExpand(createdDiscount.getId()).toUri();
        return ResponseEntity.created(location).body(convertToDto(createdDiscount));
    }

    @DeleteMapping("{id}")
    ResponseEntity<String> deleteDiscountById(@PathVariable Long id) throws NonexistingEntityException {
        service.deleteDiscountById(id);
        return new ResponseEntity<String>("Discount Deleted", HttpStatus.OK);
    }

    @PutMapping("{id}")
    DiscountDto updateDiscount(@PathVariable Long id, @RequestBody DiscountDto discountDto)
            throws InvalidRequestException, NonexistingEntityException {
        Discount discount = convertToEntity(discountDto);
        if (!id.equals(discount.getId())) {
            throw new InvalidRequestException("IDs in path (" + id + ") and discount ("
                    + discount.getId() + ") are different.");
        }
        return convertToDto(service.updateDiscount(discount));
    }

    private DiscountDto convertToDto(Discount discount) {
        return modelMapper.map(discount, DiscountDto.class);
    }

    private Discount convertToEntity(DiscountDto discountDto) {
        return modelMapper.map(discountDto, Discount.class);
    }
}