package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.AuditLogDTO;
import com.tj.inventorySpringBoot.entity.AuditLog;
import com.tj.inventorySpringBoot.entity.User;
import com.tj.inventorySpringBoot.repository.AuditLogRepository;
import com.tj.inventorySpringBoot.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new AuditLog
    public AuditLogDTO createAuditLog(AuditLogDTO auditLogDTO) {
        // Find the user by userId (replaced userName with userId)
        User user = userRepository.findByUserName(auditLogDTO.getUserName())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + auditLogDTO.getUserName()));

        // Create and set up the AuditLog entity
        AuditLog auditLog = new AuditLog();
        auditLog.setAction(auditLogDTO.getAction());
        auditLog.setEntity(auditLogDTO.getEntity());
        auditLog.setEntityId(auditLogDTO.getEntityId());
        auditLog.setUser(user);  // Setting the user for the audit log

        auditLog.setIpAddress(auditLogDTO.getIpAddress());

        // Save and return the mapped DTO
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
                .orElseThrow(() -> new EntityNotFoundException("AuditLog not found with ID: " + id));
        return mapToDTO(auditLog);
    }

    // Helper method to map entity to DTO
    private AuditLogDTO mapToDTO(AuditLog auditLog) {
        AuditLogDTO dto = new AuditLogDTO();

        dto.setAction(auditLog.getAction());
        dto.setEntity(auditLog.getEntity());
        dto.setEntityId(auditLog.getEntityId());

        dto.setUserName(auditLog.getUser().getUserName()); // Set the username from the User entity

        dto.setIpAddress(auditLog.getIpAddress());
        return dto;
    }

    // Update an existing AuditLog
    public AuditLogDTO updateAuditLog(Long id, AuditLogDTO auditLogDTO) {
        // Find existing AuditLog by ID
        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AuditLog not found with ID: " + id));

        // Update fields in the entity
        auditLog.setAction(auditLogDTO.getAction());
        auditLog.setEntity(auditLogDTO.getEntity());
        auditLog.setEntityId(auditLogDTO.getEntityId());

        // Find and set the user by userId (replaced userName with userId)
        User user = userRepository.findByUserName(auditLogDTO.getUserName())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + auditLogDTO.getUserName()));
        auditLog.setUser(user);

        // Update timestamp and IP Address

        auditLog.setIpAddress(auditLogDTO.getIpAddress());

        // Save the updated AuditLog and return the mapped DTO
        AuditLog updatedAuditLog = auditLogRepository.save(auditLog);
        return mapToDTO(updatedAuditLog);
    }

    // Delete an AuditLog by ID
    public void deleteAuditLog(Long id) {
        if (!auditLogRepository.existsById(id)) {
            throw new EntityNotFoundException("AuditLog not found with ID: " + id);
        }
        auditLogRepository.deleteById(id);
    }
}

