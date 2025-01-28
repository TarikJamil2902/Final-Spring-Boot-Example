package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.InventoryDTO;
import com.tj.inventorySpringBoot.entity.Inventory;
import com.tj.inventorySpringBoot.entity.Product;
import com.tj.inventorySpringBoot.entity.Warehouse;
import com.tj.inventorySpringBoot.repository.InventoryRepository;
import com.tj.inventorySpringBoot.repository.ProductRepository;
import com.tj.inventorySpringBoot.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository; // To handle the relationship with Product entity

    @Autowired
    private WarehouseRepository warehouseRepository; // To handle the relationship with Warehouse entity

    // Method to create a new inventory record
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = convertToEntity(inventoryDTO);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return convertToDTO(savedInventory);
    }

    // Method to update an existing inventory record
    public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
        if (inventoryOptional.isPresent()) {
            Inventory inventory = inventoryOptional.get();
            inventory.setQuantity(inventoryDTO.getQuantity());

            // Update related Product and Warehouse
            if (inventoryDTO.getProductId() != null) {
                Product product = productRepository.findById(inventoryDTO.getProductId()).orElse(null);
                inventory.setProduct(product);
            }

            if (inventoryDTO.getWarehouseId() != null) {
                Warehouse warehouse = warehouseRepository.findById(inventoryDTO.getWarehouseId()).orElse(null);
                inventory.setWarehouse(warehouse);
            }

            Inventory updatedInventory = inventoryRepository.save(inventory);
            return convertToDTO(updatedInventory);
        }
        return null; // Or throw exception if inventory not found
    }

    // Method to get all inventory records
    public List<InventoryDTO> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get an inventory record by its ID
    public InventoryDTO getInventoryById(Long id) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
        if (inventoryOptional.isPresent()) {
            return convertToDTO(inventoryOptional.get());
        }
        return null; // Or throw exception
    }

    // Method to delete an inventory record by its ID
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    // Convert InventoryDTO to Inventory entity
    private Inventory convertToEntity(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setId(inventoryDTO.getId());
        inventory.setQuantity(inventoryDTO.getQuantity());

        // Fetch the related Product and Warehouse by their IDs
        if (inventoryDTO.getProductId() != null) {
            Product product = productRepository.findById(inventoryDTO.getProductId()).orElse(null);
            inventory.setProduct(product);
        }

        if (inventoryDTO.getWarehouseId() != null) {
            Warehouse warehouse = warehouseRepository.findById(inventoryDTO.getWarehouseId()).orElse(null);
            inventory.setWarehouse(warehouse);
        }

        return inventory;
    }

    // Convert Inventory entity to InventoryDTO
    private InventoryDTO convertToDTO(Inventory inventory) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setId(inventory.getId());
        inventoryDTO.setQuantity(inventory.getQuantity());

        // Include the Product and Warehouse IDs in the DTO
        if (inventory.getProduct() != null) {
            inventoryDTO.setProductId(inventory.getProduct().getId());
        }

        if (inventory.getWarehouse() != null) {
            inventoryDTO.setWarehouseId(inventory.getWarehouse().getId());
        }

        return inventoryDTO;
    }
}
