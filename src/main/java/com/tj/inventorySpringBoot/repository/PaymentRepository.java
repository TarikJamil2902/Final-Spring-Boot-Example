package com.tj.inventorySpringBoot.repository;

import com.tj.inventorySpringBoot.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // You can add custom queries if needed
}

