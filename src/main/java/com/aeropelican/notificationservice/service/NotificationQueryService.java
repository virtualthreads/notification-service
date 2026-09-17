package com.aeropelican.notificationservice.service;

import com.aeropelican.notificationservice.dto.response.NotificationResponse;
import com.aeropelican.notificationservice.entity.NotificationDelivery;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NotificationQueryService {

    public List<NotificationResponse> getAllNotifications() {
        return Collections.emptyList();
    }

    public NotificationResponse getNotificationById(Long id) {
        return new NotificationResponse();
    }

    public List<NotificationDelivery> getDeliveriesByNotificationId(Long id) {
        return Collections.emptyList();
    }
}