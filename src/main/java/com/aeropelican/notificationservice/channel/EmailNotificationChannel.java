package com.aeropelican.notificationservice.channel;

import com.aeropelican.notificationservice.entity.Notification;
import com.aeropelican.notificationservice.entity.NotificationDelivery;
import com.aeropelican.notificationservice.entity.NotificationTemplateChannel;
import com.aeropelican.notificationservice.Repository.NotificationDeliveryRepository;
import com.aeropelican.notificationservice.Repository.NotificationTemplateChannelRepository;
import com.aeropelican.notificationservice.service.TemplateRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmailNotificationChannel implements NotificationChannel {

    private static final Logger log =
            LoggerFactory.getLogger(EmailNotificationChannel.class);

    private final JavaMailSender mailSender;
    private final TemplateRenderer templateRenderer;
    private final NotificationDeliveryRepository deliveryRepository;
    private final NotificationTemplateChannelRepository templateChannelRepository;

    public EmailNotificationChannel(
            JavaMailSender mailSender,
            TemplateRenderer templateRenderer,
            NotificationDeliveryRepository deliveryRepository,
            NotificationTemplateChannelRepository templateChannelRepository) {

        this.mailSender = mailSender;
        this.templateRenderer = templateRenderer;
        this.deliveryRepository = deliveryRepository;
        this.templateChannelRepository = templateChannelRepository;
    }

    @Override
    public String getChannelType() {
        return "EMAIL";
    }

    @Override
    public void send(NotificationDelivery delivery) {
        if (delivery == null) {
            throw new IllegalArgumentException("Delivery is required");
        }

        log.info("Starting email delivery. deliveryId={}", delivery.getId());

        try {
            if (delivery.getRecipient() == null || delivery.getRecipient().isBlank()) {
                throw new IllegalArgumentException("Email recipient is empty");
            }

            Notification notification = delivery.getNotification();
            if (notification == null) {
                throw new IllegalArgumentException("Notification is not found");
            }

            if (delivery.getTemplate() == null) {
                throw new IllegalArgumentException("Email template is not found");
            }

            NotificationTemplateChannel template = templateChannelRepository
                    .findByTemplate_IdAndChannelAndActiveTrue(
                            delivery.getTemplate().getId(),
                            NotificationTemplateChannel.NotificationChannel.EMAIL)
                    .orElseThrow(() -> new IllegalArgumentException("EMAIL template not found"));

            Map<String, Object> parameters = buildParameters(notification);

            String subject = templateRenderer.render(template.getSubject(), parameters);
            String body = templateRenderer.render(template.getBody(), parameters);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(delivery.getRecipient());
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);

            delivery.setStatus(NotificationDelivery.DeliveryStatus.SENT);
            delivery.setSentAt(LocalDateTime.now());
            delivery.setLastError(null);
            deliveryRepository.save(delivery);

            log.info("Email sent successfully. deliveryId={}", delivery.getId());

        } catch (Exception e) {
            log.error("Email delivery failed. deliveryId={}", delivery.getId(), e);

            if (delivery.getAttemptCount() == null) {
                delivery.setAttemptCount(1);
            } else {
                delivery.setAttemptCount(delivery.getAttemptCount() + 1);
            }

            delivery.setStatus(NotificationDelivery.DeliveryStatus.RETRY);
            delivery.setLastError(e.getMessage());
            deliveryRepository.save(delivery);
        }
    }

    private Map<String, Object> buildParameters(Notification notification) {
        Map<String, Object> parameters = new HashMap<>();

        if (notification == null) {
            return parameters;
        }

        return parameters;
    }
}
