package com.aeropelican.notificationservice.service;

import com.aeropelican.notificationservice.dto.NotificationResponse;
import com.aeropelican.notificationservice.entity.Notification;
import com.aeropelican.notificationservice.exception.NotificationNotFoundException;
import com.aeropelican.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class notificationservice {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationResponse getNotificationById(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() ->
                        new NotificationNotFoundException(
                                "Notification not found with id: " + id
                        )
                );

        return mapToResponse(notification);
    }

    private NotificationResponse mapToResponse(Notification notification) {

        return new NotificationResponse(
                notification.getNotificationId(),
                notification.getCustomerId(),
                notification.getNotificationTemplateId(),
                notification.getStatus()
        );
    }
}
