package com.tj.inventorySpringBoot.repository;

import com.tj.inventorySpringBoot.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
    // Custom queries can be added here if needed
}
