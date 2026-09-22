package com.aeropelican.notificationservice.controller;

import com.aeropelican.notificationservice.dispatcher.NotificationDispatcher;
import com.aeropelican.notificationservice.dto.NotificationDelivery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationDispatcher dispatcher;

    public NotificationController(NotificationDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(
            @RequestBody NotificationDelivery delivery) {

        dispatcher.dispatch(delivery);

        return ResponseEntity.ok(
                "Notification dispatched successfully to "
                        + delivery.getChannelType()
        );
    }
}