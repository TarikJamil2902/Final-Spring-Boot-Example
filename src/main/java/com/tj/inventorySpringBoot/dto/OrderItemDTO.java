package com.tj.inventorySpringBoot.dto;

public class OrderItemDTO {

    private Long id;
    private Long orderId;    // Reference to the Order ID
    private Long productId;  // Reference to the Product ID
    private Integer quantity;
    private Double price;    // Price of the product at the time of the order

    // No `createdTime`, `updatedTime` fields

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // Getters and setters
}

