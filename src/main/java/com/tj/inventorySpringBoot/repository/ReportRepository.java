package com.tj.inventorySpringBoot.repository;

import com.tj.inventorySpringBoot.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // You can add custom queries if necessary, e.g., for filtering reports by user or date range
}

