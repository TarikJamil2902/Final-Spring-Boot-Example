package com.tj.inventorySpringBoot.dto;

public class PaymentDTO {

    private Long id;
    private Long orderId;       // Reference to the Order ID
    private Double amount;
    private String paymentMethod;  // e.g., "Credit Card", "PayPal"
    private String paymentStatus;  // e.g., "PAID", "PENDING", "FAILED"

    // No `paymentDate`, `createdTime`, `updatedTime` fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Getters and setters
}

