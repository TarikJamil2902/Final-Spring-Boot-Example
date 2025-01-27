package com.tj.inventorySpringBoot.dto;



import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private Long id;
    private String customerName;
    private String customerContact;
    private List<Long> orderItemIds;  // List of order item IDs instead of full `OrderItem` objects
    private Double totalAmount;
    private String status;  // Order status as a string (e.g., "PENDING", "COMPLETED", "CANCELLED")

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public List<Long> getOrderItemIds() {
        return orderItemIds;
    }

    public void setOrderItemIds(List<Long> orderItemIds) {
        this.orderItemIds = orderItemIds;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
// No `orderDate`, `createdTime`, `updatedTime` fields

    // Getters and setters
}


