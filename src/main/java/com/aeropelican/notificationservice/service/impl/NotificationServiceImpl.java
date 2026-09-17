package com.aeropelican.notificationservice.service.impl;

import com.aeropelican.notificationservice.dto.request.CreateNotificationRequest;
import com.aeropelican.notificationservice.dto.response.NotificationResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NotificationServiceImpl {

    public NotificationResponseDTO createNotification(CreateNotificationRequest request) {
        return new NotificationResponseDTO();
    }

    public List<NotificationResponseDTO> getAllNotifications() {
        return Collections.emptyList();
    }
}