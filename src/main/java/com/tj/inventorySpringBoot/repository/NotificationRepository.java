package com.tj.inventorySpringBoot.repository;

import com.tj.inventorySpringBoot.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Additional query methods (if necessary)
}

