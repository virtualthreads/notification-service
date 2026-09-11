package com.aeropelican.notificationservice.controller;

import com.aeropelican.notificationservice.dto.NotificationResponse;
import com.aeropelican.notificationservice.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationQueryService notificationQueryService;

    public NotificationController(
            NotificationQueryService notificationQueryService) {

        this.notificationQueryService = notificationQueryService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDto>> getAllNotifications() {

        List<NotificationResponseDto> notifications =
                notificationQueryService.getAllNotifications();

        return ResponseEntity.ok(notifications);
    }

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(
            @PathVariable Long id) {

        NotificationResponse response =
                notificationService.getNotificationById(id);

        return ResponseEntity.ok(response);
    }
}