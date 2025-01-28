package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.TaxDTO;
import com.tj.inventorySpringBoot.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxes")
public class TaxController {

    @Autowired
    private TaxService taxService;

    // Endpoint to create or update a tax
    @PostMapping
    public ResponseEntity<TaxDTO> createOrUpdateTax(@RequestBody TaxDTO taxDTO) {
        TaxDTO savedTax = taxService.saveTax(taxDTO);
        return new ResponseEntity<>(savedTax, HttpStatus.CREATED);
    }

    // Endpoint to get all taxes
    @GetMapping
    public ResponseEntity<List<TaxDTO>> getAllTaxes() {
        List<TaxDTO> taxes = taxService.getAllTaxes();
        return new ResponseEntity<>(taxes, HttpStatus.OK);
    }

    // Endpoint to get a tax by its ID
    @GetMapping("/{id}")
    public ResponseEntity<TaxDTO> getTaxById(@PathVariable Long id) {
        TaxDTO taxDTO = taxService.getTaxById(id);
        if (taxDTO != null) {
            return new ResponseEntity<>(taxDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if not found
    }

    // Endpoint to delete a tax by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTax(@PathVariable Long id) {
        taxService.deleteTax(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Successfully deleted
    }
}

