package com.tj.inventorySpringBoot.dto;

public class TaxDTO {

    private Long id;
    private String name;
    private Double rate;

    // Excluding orders, createdTime, and updatedTime from the DTO

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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    // Getters and setters
}
