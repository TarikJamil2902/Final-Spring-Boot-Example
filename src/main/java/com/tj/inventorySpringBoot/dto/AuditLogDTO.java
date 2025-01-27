package com.tj.inventorySpringBoot.dto;

public class AuditLogDTO {

    private Long id;
    private String action;  // e.g., "Created Order", "Updated Inventory"
    private String details; // Detailed description of the action
    private Long userId;    // Reference to the user ID (who performed the action)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getters and setters
}
