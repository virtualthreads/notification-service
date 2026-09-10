package com.aeropelican.notificationservice.service.impl;

import com.aeropelican.notificationservice.dto.NotificationResponseDto;
import com.aeropelican.notificationservice.entities.Notification;
import com.aeropelican.notificationservice.exception.NotificationNotFoundException;
import com.aeropelican.notificationservice.mapper.NotificationMapper;
import com.aeropelican.notificationservice.repository.NotificationRepository;
import com.aeropelican.notificationservice.service.NotificationQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(
            NotificationRepository notificationRepository,
            NotificationMapper notificationMapper) {

        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public List<NotificationResponseDto> getAllNotifications() {

        return notificationRepository.findAll()
                .stream()
                .map(notificationMapper::toDto)
                .toList();
    }

    @Override
    public NotificationResponseDto getNotificationById(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(id));

        return notificationMapper.toDto(notification);
    }
}