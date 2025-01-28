package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.SupplierDTO;
import com.tj.inventorySpringBoot.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Endpoint to create or update a supplier
    @PostMapping
    public ResponseEntity<SupplierDTO> createOrUpdateSupplier(@RequestBody SupplierDTO supplierDTO) {
        SupplierDTO savedSupplier = supplierService.saveSupplier(supplierDTO);
        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    // Endpoint to get all suppliers
    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> suppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    // Endpoint to get a supplier by its ID
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        SupplierDTO supplierDTO = supplierService.getSupplierById(id);
        if (supplierDTO != null) {
            return new ResponseEntity<>(supplierDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if not found
    }

    // Endpoint to delete a supplier by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Successfully deleted
    }
}

