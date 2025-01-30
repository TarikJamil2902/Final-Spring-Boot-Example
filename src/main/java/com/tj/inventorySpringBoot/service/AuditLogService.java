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
        // Find the user by userName and throw custom exception if not found
        User user = userRepository.findById(auditLogDTO.getUserName()) // Changed from getUserId() to getUserName()
                .orElseThrow(() -> new EntityNotFoundException("User not found with userName: " + auditLogDTO.getUserName()));

        // Create and set up the AuditLog entity
        AuditLog auditLog = new AuditLog();
        auditLog.setAction(auditLogDTO.getAction());
        auditLog.setDetails(auditLogDTO.getDetails());
        // auditLog.setUser(user);

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
        dto.setId(auditLog.getId());
        dto.setAction(auditLog.getAction());
        dto.setDetails(auditLog.getDetails());
        // dto.setUserName(auditLog.getUser().getUserName()); // Changed from getUserId() to getUserName()
        return dto;
    }

    // Update an existing AuditLog
    public AuditLogDTO updateAuditLog(Long id, AuditLogDTO auditLogDTO) {
        // Find existing AuditLog by ID
        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AuditLog not found with ID: " + id));

        // Update fields in the entity
        auditLog.setAction(auditLogDTO.getAction());
        auditLog.setDetails(auditLogDTO.getDetails());

        // Find and set the user by userName
        User user = userRepository.findById(auditLogDTO.getUserName()) // Changed from getUserId() to getUserName()
                .orElseThrow(() -> new EntityNotFoundException("User not found with userName: " + auditLogDTO.getUserName()));
        // auditLog.setUser(user);

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
