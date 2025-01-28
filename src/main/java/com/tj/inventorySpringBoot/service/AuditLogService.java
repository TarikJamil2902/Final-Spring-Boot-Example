package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.AuditLogDTO;
import com.tj.inventorySpringBoot.entity.AuditLog;
import com.tj.inventorySpringBoot.entity.User;
import com.tj.inventorySpringBoot.repository.AuditLogRepository;
import com.tj.inventorySpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new AuditLog
    public AuditLogDTO createAuditLog(AuditLogDTO auditLogDTO) {
        User user = userRepository.findById(auditLogDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + auditLogDTO.getUserId()));

        AuditLog auditLog = new AuditLog();
        auditLog.setAction(auditLogDTO.getAction());
        auditLog.setDetails(auditLogDTO.getDetails());
        auditLog.setUser(user);

        AuditLog savedAuditLog = auditLogRepository.save(auditLog);
        return mapToDTO(savedAuditLog);
    }

    // Retrieve all AuditLogs
    public List<AuditLogDTO> getAllAuditLogs() {
        return auditLogRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Retrieve a specific AuditLog by ID
    public AuditLogDTO getAuditLogById(Long id) {
        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AuditLog not found with ID: " + id));
        return mapToDTO(auditLog);
    }

    // Helper method to map entity to DTO
    private AuditLogDTO mapToDTO(AuditLog auditLog) {
        AuditLogDTO dto = new AuditLogDTO();
        dto.setId(auditLog.getId());
        dto.setAction(auditLog.getAction());
        dto.setDetails(auditLog.getDetails());
        dto.setUserId(auditLog.getUser().getId());
        return dto;
    }

    // Update an existing AuditLog
    public AuditLogDTO updateAuditLog(Long id, AuditLogDTO auditLogDTO) {
        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AuditLog not found with ID: " + id));

        // Update the fields
        auditLog.setAction(auditLogDTO.getAction());
        auditLog.setDetails(auditLogDTO.getDetails());
        User user = userRepository.findById(auditLogDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + auditLogDTO.getUserId()));
        auditLog.setUser(user);

        AuditLog updatedAuditLog = auditLogRepository.save(auditLog);
        return mapToDTO(updatedAuditLog);
    }

    // Delete an AuditLog by ID
    public void deleteAuditLog(Long id) {
        if (!auditLogRepository.existsById(id)) {
            throw new RuntimeException("AuditLog not found with ID: " + id);
        }
        auditLogRepository.deleteById(id);
    }

}
