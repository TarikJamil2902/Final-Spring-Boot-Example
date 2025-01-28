package com.tj.inventorySpringBoot.repository;

import com.tj.inventorySpringBoot.entity.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Long> {
    // You can add custom queries if needed
}
