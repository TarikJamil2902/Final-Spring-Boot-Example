package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.WarehouseDTO;
import com.tj.inventorySpringBoot.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    // Create or update warehouse
    @PostMapping
    public WarehouseDTO createOrUpdateWarehouse(@RequestBody WarehouseDTO warehouseDTO) {
        return warehouseService.saveWarehouse(warehouseDTO);
    }

    // Get all warehouses
    @GetMapping
    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    // Get a warehouse by ID
    @GetMapping("/{id}")
    public WarehouseDTO getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }

    // Delete a warehouse by ID
    @DeleteMapping("/{id}")
    public void deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
    }
}

