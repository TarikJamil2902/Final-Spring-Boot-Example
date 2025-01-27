package com.tj.inventorySpringBoot.dto;

import java.util.List;

public class PurchaseOrderDTO {

    private Long id;
    private Long supplierId;  // Reference to the Supplier ID
    private List<Long> purchaseOrderItemIds;  // List of IDs of PurchaseOrderItems
    private Double totalCost;
    private String status;  // Status as a string (e.g., "PENDING", "COMPLETED", "CANCELLED")

    // No `orderDate`, `createdTime`, `updatedTime` fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public List<Long> getPurchaseOrderItemIds() {
        return purchaseOrderItemIds;
    }

    public void setPurchaseOrderItemIds(List<Long> purchaseOrderItemIds) {
        this.purchaseOrderItemIds = purchaseOrderItemIds;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters and setters
}

