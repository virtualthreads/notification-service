package com.aeropelican.notificationservice.controller;

import com.aeropelican.notificationservice.dto.NotificationResponse;
import com.aeropelican.notificationservice.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

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