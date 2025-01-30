package com.tj.inventorySpringBoot.dto;

public class ReportDTO {

    private Long id;
    private String title;   // Report title, like "Sales Report", "Inventory Report"
    private String content; // Detailed content or data of the report
    private String createdByUserName; // userName of the user who created the report

    // No `createdTime` field, as it's not required in the DTO

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }

    // Getters and setters
}
