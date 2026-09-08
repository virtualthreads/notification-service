package com.aeropelican.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String body) {
        if (to == null || to.isEmpty()) {
            throw new RuntimeException("To address cannot be null/empty");
        } else if (subject == null || subject.isEmpty()) {
            throw new RuntimeException("Subject cannot be null or empty");
        } else if (body == null || body.isEmpty()) {
            throw new RuntimeException("Body should not be empty");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }
}
