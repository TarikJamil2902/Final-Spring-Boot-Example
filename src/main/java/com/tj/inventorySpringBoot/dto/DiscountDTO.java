package com.tj.inventorySpringBoot.dto;

import java.time.LocalDateTime;

public class DiscountDTO {

    private Long discountId; // Primary Key
    private String discountCode; // Discount code (e.g., "WINTER2025")
    private String description; // Description of the discount
    private String discountType; // Discount type (e.g., percentage, fixed amount)
    private Double amount; // Amount of discount (either percentage or fixed amount)
    private LocalDateTime validFrom; // Start date of the discount validity
    private LocalDateTime validUntil; // End date of the discount validity
    private String status; // Discount status (e.g., active, expired)
    private String applicableTo; // Applicable to (e.g., product, category)

    // Getters and Setters

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplicableTo() {
        return applicableTo;
    }

    public void setApplicableTo(String applicableTo) {
        this.applicableTo = applicableTo;
    }
}
