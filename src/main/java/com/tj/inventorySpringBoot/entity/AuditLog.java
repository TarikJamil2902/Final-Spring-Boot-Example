package com.tj.inventorySpringBoot.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action; // e.g., "Created Order", "Updated Inventory"
    private String details; // Detailed description of the action

    // @ManyToOne
    // private User user; // Reference to the user who performed the action

    private OffsetDateTime timestamp; // Using OffsetDateTime for consistency with UTC

    @PrePersist
    protected void onCreate() {
        this.timestamp = OffsetDateTime.now(); // Set timestamp to current time
    }

    // Getters and setters
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

    // public User getUser() {
    //     return user;
    // }

    // public void setUser(User user) {
    //     this.user = user;
    // }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
