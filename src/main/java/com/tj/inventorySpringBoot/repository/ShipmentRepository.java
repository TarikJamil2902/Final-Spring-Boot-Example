package com.tj.inventorySpringBoot.repository;

import com.tj.inventorySpringBoot.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    // Custom queries can be added if necessary
}

