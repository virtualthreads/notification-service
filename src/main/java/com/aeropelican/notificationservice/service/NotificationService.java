package com.aeropelican.notificationservice.service;

import com.aeropelican.notificationservice.dto.request.CreateNotificationRequest;
import com.aeropelican.notificationservice.dto.response.NotificationResponse;

public interface NotificationService {

    NotificationResponse createNotification(CreateNotificationRequest request);
}