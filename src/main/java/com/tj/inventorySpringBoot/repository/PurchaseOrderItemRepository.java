package com.tj.inventorySpringBoot.repository;

import com.tj.inventorySpringBoot.entity.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long> {
    // You can add custom queries if needed
}
