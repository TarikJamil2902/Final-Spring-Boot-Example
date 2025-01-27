package com.tj.inventorySpringBoot.dto;

public class UserDTO {

    private Long id;
    private String username;
    private String role; // Role is stored as a string, e.g., ADMIN, MANAGER, STAFF
    private String email;
    private String phone;

    // Excluding auditLogs, createdTime, and updatedTime from the DTO

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getters and setters
}

