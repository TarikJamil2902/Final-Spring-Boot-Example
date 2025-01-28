package com.tj.inventorySpringBoot.repository;

import com.tj.inventorySpringBoot.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    // Custom query methods can be added here if needed
}

