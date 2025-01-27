package com.tj.inventorySpringBoot.dto;

import java.util.List;

public class CategoryDTO {

    private Long id;
    private String name;
    private String description;
    private List<Long> productIds; // Reference to product IDs, as you don't want to expose full products

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    // No `createdTime`, `updatedTime` fields

    // Getters and setters
}

