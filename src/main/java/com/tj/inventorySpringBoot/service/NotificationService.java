package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.NotificationDTO;
import com.tj.inventorySpringBoot.entity.Notification;
import com.tj.inventorySpringBoot.enums.NotificationType;
import com.tj.inventorySpringBoot.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Method to create a new notification
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = convertToEntity(notificationDTO);
        Notification savedNotification = notificationRepository.save(notification);
        return convertToDTO(savedNotification);
    }

    // Method to update an existing notification
    public NotificationDTO updateNotification(Long id, NotificationDTO notificationDTO) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setTitle(notificationDTO.getTitle());
            notification.setMessage(notificationDTO.getMessage());
            notification.setIsRead(notificationDTO.getIsRead());

            if (notificationDTO.getType() != null) {
                notification.setType(NotificationType.valueOf(notificationDTO.getType()));
            }

            Notification updatedNotification = notificationRepository.save(notification);
            return convertToDTO(updatedNotification);
        }
        return null; // Or throw exception if notification not found
    }

    // Method to get all notifications
    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get a notification by its ID
    public NotificationDTO getNotificationById(Long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            return convertToDTO(notificationOptional.get());
        }
        return null; // Or throw exception
    }

    // Method to mark a notification as read
    public NotificationDTO markAsRead(Long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setIsRead(true);
            notificationRepository.save(notification);
            return convertToDTO(notification);
        }
        return null; // Handle as needed (e.g., return 404 or throw exception)
    }

    // Method to delete a notification by its ID
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    // Convert NotificationDTO to Notification entity
    private Notification convertToEntity(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setId(notificationDTO.getId());
        notification.setTitle(notificationDTO.getTitle());
        notification.setMessage(notificationDTO.getMessage());
        notification.setIsRead(notificationDTO.getIsRead());

        if (notificationDTO.getType() != null) {
            notification.setType(NotificationType.valueOf(notificationDTO.getType()));
        }

        return notification;
    }

    // Convert Notification entity to NotificationDTO
    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notification.getId());
        notificationDTO.setTitle(notification.getTitle());
        notificationDTO.setMessage(notification.getMessage());
        notificationDTO.setIsRead(notification.getIsRead());

        if (notification.getType() != null) {
            notificationDTO.setType(notification.getType().name());
        }

        return notificationDTO;
    }
}
