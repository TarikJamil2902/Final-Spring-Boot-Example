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

    // Endpoint to create a new purchase order item
    @PostMapping("/create")
    public ResponseEntity<PurchaseOrderItemDTO> createPurchaseOrderItem(@RequestBody PurchaseOrderItemDTO purchaseOrderItemDTO) {
        PurchaseOrderItemDTO createdPurchaseOrderItem = purchaseOrderItemService.createPurchaseOrderItem(purchaseOrderItemDTO);
        return new ResponseEntity<>(createdPurchaseOrderItem, HttpStatus.CREATED);
    }

    // Endpoint to update an existing purchase order item
    @PutMapping("/update/{id}")
    public ResponseEntity<PurchaseOrderItemDTO> updatePurchaseOrderItem(@PathVariable Long id, @RequestBody PurchaseOrderItemDTO purchaseOrderItemDTO) {
        PurchaseOrderItemDTO updatedPurchaseOrderItem = purchaseOrderItemService.updatePurchaseOrderItem(id, purchaseOrderItemDTO);
        if (updatedPurchaseOrderItem != null) {
            return new ResponseEntity<>(updatedPurchaseOrderItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Purchase order item not found
    }

    // Endpoint to get all purchase order items
    @GetMapping
    public ResponseEntity<List<PurchaseOrderItemDTO>> getAllPurchaseOrderItems() {
        List<PurchaseOrderItemDTO> purchaseOrderItemDTOs = purchaseOrderItemService.getAllPurchaseOrderItems();
        return new ResponseEntity<>(purchaseOrderItemDTOs, HttpStatus.OK);
    }

    // Endpoint to get a purchase order item by its ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderItemDTO> getPurchaseOrderItemById(@PathVariable Long id) {
        PurchaseOrderItemDTO purchaseOrderItemDTO = purchaseOrderItemService.getPurchaseOrderItemById(id);
        if (purchaseOrderItemDTO != null) {
            return new ResponseEntity<>(purchaseOrderItemDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Purchase order item not found
    }

    // Endpoint to delete a purchase order item by its ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePurchaseOrderItem(@PathVariable Long id) {
        purchaseOrderItemService.deletePurchaseOrderItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // No content to return
    }
}
