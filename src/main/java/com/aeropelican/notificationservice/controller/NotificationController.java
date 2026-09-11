package com.aeropelican.notificationservice.controller;

import com.aeropelican.notificationservice.dto.request.CreateNotificationRequest;
import com.aeropelican.notificationservice.dto.response.NotificationResponse;
import com.aeropelican.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(
            @Valid @RequestBody CreateNotificationRequest request) {

        NotificationResponse response =
                notificationService.createNotification(request);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(response);
    }
}