package com.aeropelican.notificationservice.mapper;

import com.aeropelican.notificationservice.dto.request.CreateNotificationRequest;
import com.aeropelican.notificationservice.dto.response.NotificationResponse;
import com.aeropelican.notificationservice.entity.Customer;
import com.aeropelican.notificationservice.entity.Notification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {

    public Notification toEntity(
            CreateNotificationRequest request,
            Customer customer) {

        return Notification.builder()
                .eventId(request.getEventId())
                .eventType(request.getEventType())
                .customer(customer)
                .payload(request.getPayload())
                .status(Notification.NotificationStatus.RECEIVED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public NotificationResponse toResponse(Notification notification) {

        return NotificationResponse.builder()
                .notificationId(notification.getId())
                .eventId(notification.getEventId())
                .eventType(notification.getEventType())
                .customerCode(
                        notification.getCustomer() != null
                                ? notification.getCustomer().getCustomerCode()
                                : null
                )
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}