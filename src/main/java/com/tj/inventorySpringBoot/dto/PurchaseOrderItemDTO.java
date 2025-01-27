package com.tj.inventorySpringBoot.dto;

public class PurchaseOrderItemDTO {

    private Long id;
    private Long purchaseOrderId;  // Reference to the PurchaseOrder ID
    private Long productId;  // Reference to the Product ID
    private Integer quantity;
    private Double costPerUnit;

    // No `createdTime`, `updatedTime` fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(Double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    // Getters and setters
}

