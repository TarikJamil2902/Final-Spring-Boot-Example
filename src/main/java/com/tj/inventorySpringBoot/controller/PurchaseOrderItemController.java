package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.PurchaseOrderItemDTO;
import com.tj.inventorySpringBoot.service.PurchaseOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-order-items")
public class PurchaseOrderItemController {

    @Autowired
    private PurchaseOrderItemService purchaseOrderItemService;

    // Endpoint to create or update a purchase order item
    @PostMapping
    public ResponseEntity<PurchaseOrderItemDTO> createOrUpdatePurchaseOrderItem(@RequestBody PurchaseOrderItemDTO purchaseOrderItemDTO) {
        PurchaseOrderItemDTO savedPurchaseOrderItem = purchaseOrderItemService.savePurchaseOrderItem(purchaseOrderItemDTO);
        return new ResponseEntity<>(savedPurchaseOrderItem, HttpStatus.CREATED);
    }

    // Endpoint to get all purchase order items
    @GetMapping
    public ResponseEntity<List<PurchaseOrderItemDTO>> getAllPurchaseOrderItems() {
        List<PurchaseOrderItemDTO> purchaseOrderItems = purchaseOrderItemService.getAllPurchaseOrderItems();
        return new ResponseEntity<>(purchaseOrderItems, HttpStatus.OK);
    }

    // Endpoint to get a purchase order item by its ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderItemDTO> getPurchaseOrderItemById(@PathVariable Long id) {
        PurchaseOrderItemDTO purchaseOrderItemDTO = purchaseOrderItemService.getPurchaseOrderItemById(id);
        if (purchaseOrderItemDTO != null) {
            return new ResponseEntity<>(purchaseOrderItemDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete a purchase order item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseOrderItem(@PathVariable Long id) {
        purchaseOrderItemService.deletePurchaseOrderItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

