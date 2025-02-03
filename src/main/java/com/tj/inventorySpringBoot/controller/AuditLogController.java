package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.AuditLogDTO;
import com.tj.inventorySpringBoot.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditlogs")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    // Create a new AuditLog
    @PostMapping("/create")
    public ResponseEntity<AuditLogDTO> createAuditLog(@RequestBody AuditLogDTO auditLogDTO) {
        AuditLogDTO createdAuditLog = auditLogService.createAuditLog(auditLogDTO);
        return ResponseEntity.ok(createdAuditLog);
    }

    // Retrieve all AuditLogs
    @GetMapping
    public ResponseEntity<List<AuditLogDTO>> getAllAuditLogs() {
        List<AuditLogDTO> auditLogs = auditLogService.getAllAuditLogs();
        return ResponseEntity.ok(auditLogs);
    }
    // Update an existing AuditLog
    @PutMapping("/update/{id}")
    public ResponseEntity<AuditLogDTO> updateAuditLog(@PathVariable Long id, @RequestBody AuditLogDTO auditLogDTO) {
        AuditLogDTO updatedAuditLog = auditLogService.updateAuditLog(id, auditLogDTO);
        return ResponseEntity.ok(updatedAuditLog);
    }

    // Retrieve a specific AuditLog by ID
    @GetMapping("/{id}")
    public ResponseEntity<AuditLogDTO> getAuditLogById(@PathVariable Long id) {
        AuditLogDTO auditLog = auditLogService.getAuditLogById(id);
        return ResponseEntity.ok(auditLog);
    }



    // Delete an AuditLog by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuditLog(@PathVariable Long id) {
        auditLogService.deleteAuditLog(id);
        return ResponseEntity.noContent().build();
    }
}
