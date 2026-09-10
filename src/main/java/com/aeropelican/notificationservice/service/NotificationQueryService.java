package com.aeropelican.notificationservice.service;

import com.aeropelican.notificationservice.dto.NotificationResponseDto;

import java.util.List;

public interface NotificationQueryService {

    List<NotificationResponseDto> getAllNotifications();

    NotificationResponseDto getNotificationById(Long id);
}