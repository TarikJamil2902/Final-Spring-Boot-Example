package com.tj.inventorySpringBoot.repository;

import com.tj.inventorySpringBoot.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    // Custom query methods can be added here if needed
}
