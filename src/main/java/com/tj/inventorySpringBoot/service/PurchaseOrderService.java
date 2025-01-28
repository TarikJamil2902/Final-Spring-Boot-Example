package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.PurchaseOrderDTO;
import com.tj.inventorySpringBoot.entity.PurchaseOrder;
import com.tj.inventorySpringBoot.entity.PurchaseOrderItem;
import com.tj.inventorySpringBoot.entity.Supplier;
import com.tj.inventorySpringBoot.enums.PurchaseOrderStatus;
import com.tj.inventorySpringBoot.repository.PurchaseOrderItemRepository;
import com.tj.inventorySpringBoot.repository.PurchaseOrderRepository;
import com.tj.inventorySpringBoot.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private SupplierRepository supplierRepository; // Assuming Supplier repository is available

    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository; // Assuming PurchaseOrderItem repository is available

    // Save or update a purchase order
    public PurchaseOrderDTO savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = convertToEntity(purchaseOrderDTO);
        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        return convertToDTO(savedPurchaseOrder);
    }

    // Get all purchase orders
    public List<PurchaseOrderDTO> getAllPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        return purchaseOrders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a purchase order by its ID
    public PurchaseOrderDTO getPurchaseOrderById(Long id) {
        Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(id);
        if (purchaseOrderOptional.isPresent()) {
            return convertToDTO(purchaseOrderOptional.get());
        }
        return null; // You can throw an exception or return 404 in the controller
    }

    // Delete a purchase order by its ID
    public void deletePurchaseOrder(Long id) {
        purchaseOrderRepository.deleteById(id);
    }

    // Convert PurchaseOrderDTO to PurchaseOrder entity
    private PurchaseOrder convertToEntity(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(purchaseOrderDTO.getId());
        purchaseOrder.setTotalCost(purchaseOrderDTO.getTotalCost());
        purchaseOrder.setStatus(PurchaseOrderStatus.valueOf(purchaseOrderDTO.getStatus()));

        // Set the Supplier
        Supplier supplier = supplierRepository.findById(purchaseOrderDTO.getSupplierId()).orElse(null);
        purchaseOrder.setSupplier(supplier);

        // Set PurchaseOrderItems
        List<PurchaseOrderItem> purchaseOrderItems = purchaseOrderItemRepository.findAllById(purchaseOrderDTO.getPurchaseOrderItemIds());
        purchaseOrder.setPurchaseOrderItems(purchaseOrderItems);

        return purchaseOrder;
    }

    // Convert PurchaseOrder entity to PurchaseOrderDTO
    private PurchaseOrderDTO convertToDTO(PurchaseOrder purchaseOrder) {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setId(purchaseOrder.getId());
        purchaseOrderDTO.setTotalCost(purchaseOrder.getTotalCost());
        purchaseOrderDTO.setStatus(purchaseOrder.getStatus().name());

        // Set Supplier ID
        if (purchaseOrder.getSupplier() != null) {
            purchaseOrderDTO.setSupplierId(purchaseOrder.getSupplier().getId());
        }

        // Set PurchaseOrderItem IDs
        if (purchaseOrder.getPurchaseOrderItems() != null) {
            List<Long> purchaseOrderItemIds = purchaseOrder.getPurchaseOrderItems().stream()
                    .map(poi -> poi.getId())
                    .collect(Collectors.toList());
            purchaseOrderDTO.setPurchaseOrderItemIds(purchaseOrderItemIds);
        }

        return purchaseOrderDTO;
    }
}

