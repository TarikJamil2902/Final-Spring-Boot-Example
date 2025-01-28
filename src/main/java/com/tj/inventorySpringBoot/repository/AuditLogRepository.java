package com.tj.inventorySpringBoot.repository;

import com.tj.inventorySpringBoot.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
