package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.DiscountDTO;
import com.tj.inventorySpringBoot.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    // Endpoint to create or update a discount
    @PostMapping
    public ResponseEntity<DiscountDTO> createOrUpdateDiscount(@RequestBody DiscountDTO discountDTO) {
        DiscountDTO savedDiscount = discountService.saveDiscount(discountDTO);
        return new ResponseEntity<>(savedDiscount, HttpStatus.CREATED);
    }

    // Endpoint to get all discounts
    @GetMapping
    public ResponseEntity<List<DiscountDTO>> getAllDiscounts() {
        List<DiscountDTO> discounts = discountService.getAllDiscounts();
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    // Endpoint to get a discount by its ID
    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO> getDiscountById(@PathVariable Long id) {
        DiscountDTO discountDTO = discountService.getDiscountById(id);
        if (discountDTO != null) {
            return new ResponseEntity<>(discountDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete a discount by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

