package com.aeropelican.notificationservice.mapper;

import com.aeropelican.notificationservice.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationResponseDto toDto(Notification notification) {

        return new NotificationResponseDto(
                notification.getId(),
                notification.getEventId(),
                notification.getEventType(),
                notification.getCustomerId(),
                notification.getPayload(),
                notification.getStatus(),
                notification.getCreatedAt(),
                notification.getUpdatedAt()
        );
    }
}