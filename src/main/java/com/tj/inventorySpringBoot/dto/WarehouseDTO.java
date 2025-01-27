package com.tj.inventorySpringBoot.dto;

public class WarehouseDTO {

    private Long id;
    private String name;
    private String location;

    // Excluding inventories, createdTime, and updatedTime from the DTO

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Getters and setters
}

