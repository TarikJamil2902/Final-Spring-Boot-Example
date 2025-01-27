package com.tj.inventorySpringBoot.enums;

public enum Role {
    ADMIN,   // Has full control of the system
    MANAGER, // Can manage inventory, orders, and reports
    STAFF    // Limited access, typically for handling orders or stock
}