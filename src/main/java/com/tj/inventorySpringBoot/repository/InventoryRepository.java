package com.tj.inventorySpringBoot.repository;

import com.tj.inventorySpringBoot.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Custom query methods can be added here if needed (e.g., filtering by warehouse or product)
}

