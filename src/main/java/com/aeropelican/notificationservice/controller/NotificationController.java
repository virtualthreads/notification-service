package com.aeropelican.notificationservice.controller;

import com.aeropelican.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notify")
@RequiredArgsConstructor
public class NotificationController {

    private final EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<String> sendMail(@RequestParam String to,
                                           @RequestParam String subject,
                                           @RequestParam String body) {
        emailService.sendMail(to, subject, body);
        return ResponseEntity.ok("Mail has been sent!");
    }
}
