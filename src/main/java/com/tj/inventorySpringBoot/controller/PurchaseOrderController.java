package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.PurchaseOrderDTO;
import com.tj.inventorySpringBoot.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    // Endpoint to create or update a purchase order
    @PostMapping
    public ResponseEntity<PurchaseOrderDTO> createOrUpdatePurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrderDTO savedPurchaseOrder = purchaseOrderService.savePurchaseOrder(purchaseOrderDTO);
        return new ResponseEntity<>(savedPurchaseOrder, HttpStatus.CREATED);
    }

    // Endpoint to get all purchase orders
    @GetMapping
    public ResponseEntity<List<PurchaseOrderDTO>> getAllPurchaseOrders() {
        List<PurchaseOrderDTO> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();
        return new ResponseEntity<>(purchaseOrders, HttpStatus.OK);
    }

    // Endpoint to get a purchase order by its ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> getPurchaseOrderById(@PathVariable Long id) {
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderService.getPurchaseOrderById(id);
        if (purchaseOrderDTO != null) {
            return new ResponseEntity<>(purchaseOrderDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete a purchase order by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
        purchaseOrderService.deletePurchaseOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
