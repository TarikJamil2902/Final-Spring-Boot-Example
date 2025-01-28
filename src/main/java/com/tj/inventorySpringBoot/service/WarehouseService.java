package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.WarehouseDTO;
import com.tj.inventorySpringBoot.entity.Warehouse;
import com.tj.inventorySpringBoot.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    // Create a new warehouse
    public WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = convertToEntity(warehouseDTO);
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return convertToDTO(savedWarehouse);
    }

    // Update an existing warehouse
    public WarehouseDTO updateWarehouse(Long id, WarehouseDTO warehouseDTO) {
        Optional<Warehouse> existingWarehouseOptional = warehouseRepository.findById(id);
        if (existingWarehouseOptional.isPresent()) {
            Warehouse existingWarehouse = existingWarehouseOptional.get();

            // Update fields
            existingWarehouse.setName(warehouseDTO.getName());
            existingWarehouse.setLocation(warehouseDTO.getLocation());

            Warehouse updatedWarehouse = warehouseRepository.save(existingWarehouse);
            return convertToDTO(updatedWarehouse);
        }
        return null; // Handle not found case as needed
    }

    // Get all warehouses
    public List<WarehouseDTO> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a warehouse by ID
    public WarehouseDTO getWarehouseById(Long id) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        return warehouseOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete a warehouse by ID
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    // Convert WarehouseDTO to Warehouse entity
    private Warehouse convertToEntity(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseDTO.getId());
        warehouse.setName(warehouseDTO.getName());
        warehouse.setLocation(warehouseDTO.getLocation());
        return warehouse;
    }

    // Convert Warehouse entity to WarehouseDTO
    private WarehouseDTO convertToDTO(Warehouse warehouse) {
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        warehouseDTO.setId(warehouse.getId());
        warehouseDTO.setName(warehouse.getName());
        warehouseDTO.setLocation(warehouse.getLocation());
        return warehouseDTO;
    }
}
