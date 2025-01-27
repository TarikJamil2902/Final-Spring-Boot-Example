package com.tj.inventorySpringBoot.entity;



import com.tj.inventorySpringBoot.enums.OrderStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerContact;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // e.g., PENDING, COMPLETED, CANCELLED

    private LocalDateTime orderDate;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
// Getters and setters
}
