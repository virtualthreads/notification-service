package com.aeropelican.notificationservice.service;

import com.aeropelican.notificationservice.dto.NotificationResponse;
import com.aeropelican.notificationservice.entities.Notification;
import com.aeropelican.notificationservice.exception.NotificationNotFoundException;
import com.aeropelican.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class notificationservice {

    private final NotificationRepository notificationRepository;

    public notificationservice(NotificationRepository notificationRepository) {
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
                notification.getId(),
                notification.getCustomerId(),
                null,
                notification.getStatus()
        );
    }
}
