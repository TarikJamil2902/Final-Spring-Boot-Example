package com.tj.inventorySpringBoot.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DiscountDTO {

    private Long id;
    private String name;  // e.g., "Winter Sale"
    private Double percentage; // Discount percentage
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Long> orderIds; // References to orders (order IDs)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }
// No `createdTime`, `updatedTime` fields

    // Getters and setters
}

