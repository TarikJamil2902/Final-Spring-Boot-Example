package com.tj.inventorySpringBoot.enums;

public enum OrderStatus {
    PENDING,     // Order is yet to be processed
    PROCESSING,  // Order is being prepared
    SHIPPED,     // Order has been shipped
    DELIVERED,   // Order has been delivered to the customer
    CANCELLED    // Order was cancelled
}
