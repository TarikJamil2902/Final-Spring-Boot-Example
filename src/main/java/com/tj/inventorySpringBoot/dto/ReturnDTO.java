package com.tj.inventorySpringBoot.dto;

public class ReturnDTO {

    private Long id;
    private Long customerOrderId; // ID of the associated customer order (if applicable)
    private Long supplierReturnId; // ID of the associated supplier return (if applicable)
    private String reason; // Reason for the return
    private Integer quantity;

    // Excluding createdTime and updatedTime from the DTO

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(Long customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public Long getSupplierReturnId() {
        return supplierReturnId;
    }

    public void setSupplierReturnId(Long supplierReturnId) {
        this.supplierReturnId = supplierReturnId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Getters and setters
}


