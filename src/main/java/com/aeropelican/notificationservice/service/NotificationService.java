package com.aeropelican.notificationservice.service;

import com.aeropelican.notificationservice.dto.response.NotificationResponseDTO;
import com.aeropelican.notificationservice.entity.Notification;
import com.aeropelican.notificationservice.exception.NotificationNotFoundException;
import com.aeropelican.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationResponseDTO getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() ->
                        new NotificationNotFoundException(
                                "Notification not found with id: " + id
                        )
                );

        return mapToResponse(notification);
    }

    private NotificationResponseDTO mapToResponse(Notification notification) {
        return new NotificationResponseDTO(
                notification.getId(),
                notification.getCustomerId(),
                notification.getNotificationTemplateId(),
                notification.getEventId(),
                notification.getEventType(),
                notification.getPayload(),
                notification.getStatus(),
                notification.getCreatedAt(),
                notification.getUpdatedAt()
        );
    }
}