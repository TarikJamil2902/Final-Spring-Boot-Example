package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.TaxDTO;
import com.tj.inventorySpringBoot.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxes")
public class TaxController {

    @Autowired
    private TaxService taxService;

    // Create a new tax
    @PostMapping("/create")
    public ResponseEntity<TaxDTO> createTax(@RequestBody TaxDTO taxDTO) {
        TaxDTO createdTax = taxService.createTax(taxDTO);
        return ResponseEntity.ok(createdTax);
    }

    // Update an existing tax
    @PutMapping("/update/{id}")
    public ResponseEntity<TaxDTO> updateTax(@PathVariable Long id, @RequestBody TaxDTO taxDTO) {
        TaxDTO updatedTax = taxService.updateTax(id, taxDTO);
        if (updatedTax != null) {
            return ResponseEntity.ok(updatedTax);
        }
        return ResponseEntity.notFound().build();
    }

    // Get all taxes
    @GetMapping
    public ResponseEntity<List<TaxDTO>> getAllTaxes() {
        List<TaxDTO> taxes = taxService.getAllTaxes();
        return ResponseEntity.ok(taxes);
    }

    // Get a tax by ID
    @GetMapping("/{id}")
    public ResponseEntity<TaxDTO> getTaxById(@PathVariable Long id) {
        TaxDTO taxDTO = taxService.getTaxById(id);
        if (taxDTO != null) {
            return ResponseEntity.ok(taxDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a tax by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTax(@PathVariable Long id) {
        taxService.deleteTax(id);
        return ResponseEntity.noContent().build();
    }
}
