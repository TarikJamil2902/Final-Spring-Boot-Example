package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.PurchaseOrderItemDTO;
import com.tj.inventorySpringBoot.entity.Product;
import com.tj.inventorySpringBoot.entity.PurchaseOrder;
import com.tj.inventorySpringBoot.entity.PurchaseOrderItem;
import com.tj.inventorySpringBoot.repository.ProductRepository;
import com.tj.inventorySpringBoot.repository.PurchaseOrderItemRepository;
import com.tj.inventorySpringBoot.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PurchaseOrderItemService {

    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    // Create a new purchase order item
    public PurchaseOrderItemDTO createPurchaseOrderItem(PurchaseOrderItemDTO purchaseOrderItemDTO) {
        PurchaseOrderItem purchaseOrderItem = convertToEntity(purchaseOrderItemDTO);
        PurchaseOrderItem savedPurchaseOrderItem = purchaseOrderItemRepository.save(purchaseOrderItem);
        return convertToDTO(savedPurchaseOrderItem);
    }

    // Update an existing purchase order item
    public PurchaseOrderItemDTO updatePurchaseOrderItem(Long id, PurchaseOrderItemDTO purchaseOrderItemDTO) {
        Optional<PurchaseOrderItem> existingPurchaseOrderItemOptional = purchaseOrderItemRepository.findById(id);
        if (existingPurchaseOrderItemOptional.isPresent()) {
            PurchaseOrderItem existingPurchaseOrderItem = existingPurchaseOrderItemOptional.get();
            // Update fields
            existingPurchaseOrderItem.setQuantity(purchaseOrderItemDTO.getQuantity());
            existingPurchaseOrderItem.setCostPerUnit(purchaseOrderItemDTO.getCostPerUnit());

            // Set the PurchaseOrder
            PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderItemDTO.getPurchaseOrderId()).orElse(null);
            existingPurchaseOrderItem.setPurchaseOrder(purchaseOrder);

            // Set the Product
            Product product = productRepository.findById(purchaseOrderItemDTO.getProductId()).orElse(null);
            existingPurchaseOrderItem.setProduct(product);

            PurchaseOrderItem updatedPurchaseOrderItem = purchaseOrderItemRepository.save(existingPurchaseOrderItem);
            return convertToDTO(updatedPurchaseOrderItem);
        }
        return null; // Return null if not found, or you can throw an exception
    }

    // Get all purchase order items
    public List<PurchaseOrderItemDTO> getAllPurchaseOrderItems() {
        List<PurchaseOrderItem> purchaseOrderItems = purchaseOrderItemRepository.findAll();
        return purchaseOrderItems.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a purchase order item by its ID
    public PurchaseOrderItemDTO getPurchaseOrderItemById(Long id) {
        Optional<PurchaseOrderItem> purchaseOrderItemOptional = purchaseOrderItemRepository.findById(id);
        if (purchaseOrderItemOptional.isPresent()) {
            return convertToDTO(purchaseOrderItemOptional.get());
        }
        return null; // You can throw an exception or return 404 in the controller
    }

    // Delete a purchase order item by its ID
    public void deletePurchaseOrderItem(Long id) {
        purchaseOrderItemRepository.deleteById(id);
    }

    // Convert PurchaseOrderItemDTO to PurchaseOrderItem entity
    private PurchaseOrderItem convertToEntity(PurchaseOrderItemDTO purchaseOrderItemDTO) {
        PurchaseOrderItem purchaseOrderItem = new PurchaseOrderItem();
        purchaseOrderItem.setId(purchaseOrderItemDTO.getId());
        purchaseOrderItem.setQuantity(purchaseOrderItemDTO.getQuantity());
        purchaseOrderItem.setCostPerUnit(purchaseOrderItemDTO.getCostPerUnit());

        // Set the PurchaseOrder
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderItemDTO.getPurchaseOrderId()).orElse(null);
        purchaseOrderItem.setPurchaseOrder(purchaseOrder);

        // Set the Product
        Product product = productRepository.findById(purchaseOrderItemDTO.getProductId()).orElse(null);
        purchaseOrderItem.setProduct(product);

        return purchaseOrderItem;
    }

    // Convert PurchaseOrderItem entity to PurchaseOrderItemDTO
    private PurchaseOrderItemDTO convertToDTO(PurchaseOrderItem purchaseOrderItem) {
        PurchaseOrderItemDTO purchaseOrderItemDTO = new PurchaseOrderItemDTO();
        purchaseOrderItemDTO.setId(purchaseOrderItem.getId());
        purchaseOrderItemDTO.setQuantity(purchaseOrderItem.getQuantity());
        purchaseOrderItemDTO.setCostPerUnit(purchaseOrderItem.getCostPerUnit());

        // Set PurchaseOrder ID
        if (purchaseOrderItem.getPurchaseOrder() != null) {
            purchaseOrderItemDTO.setPurchaseOrderId(purchaseOrderItem.getPurchaseOrder().getId());
        }

        // Set Product ID
        if (purchaseOrderItem.getProduct() != null) {
            purchaseOrderItemDTO.setProductId(purchaseOrderItem.getProduct().getId());
        }

        return purchaseOrderItemDTO;
    }
}
