package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.InventoryDTO;
import com.tj.inventorySpringBoot.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Endpoint to create or update an inventory record
    @PostMapping
    public ResponseEntity<InventoryDTO> createOrUpdateInventory(@RequestBody InventoryDTO inventoryDTO) {
        InventoryDTO savedInventory = inventoryService.saveInventory(inventoryDTO);
        return new ResponseEntity<>(savedInventory, HttpStatus.CREATED);
    }

    // Endpoint to get all inventory records
    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventories() {
        List<InventoryDTO> inventories = inventoryService.getAllInventories();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    // Endpoint to get an inventory record by its ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Long id) {
        InventoryDTO inventoryDTO = inventoryService.getInventoryById(id);
        if (inventoryDTO != null) {
            return new ResponseEntity<>(inventoryDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete an inventory record by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

