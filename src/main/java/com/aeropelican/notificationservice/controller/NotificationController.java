package com.aeropelican.notificationservice.controller;

import com.aeropelican.notificationservice.dto.request.CreateNotificationRequest;
import com.aeropelican.notificationservice.dto.response.NotificationResponse;
import com.aeropelican.notificationservice.entity.NotificationDelivery;
import com.aeropelican.notificationservice.service.NotificationQueryService;
import com.aeropelican.notificationservice.service.impl.NotificationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationServiceImpl notificationService;
    private final NotificationQueryService queryService;

    public NotificationController(NotificationServiceImpl notificationService, NotificationQueryService queryService) {
        this.notificationService = notificationService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@Valid @RequestBody CreateNotificationRequest request) {
        return new ResponseEntity<>(notificationService.createNotification(request), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        return ResponseEntity.ok(queryService.getAllNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(queryService.getNotificationById(id));
    }

    @GetMapping("/{id}/deliveries")
    public ResponseEntity<List<NotificationDelivery>> getNotificationDeliveries(@PathVariable Long id) {
        return ResponseEntity.ok(queryService.getDeliveriesByNotificationId(id));
    }
}