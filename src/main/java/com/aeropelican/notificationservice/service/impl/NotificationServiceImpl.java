package com.aeropelican.notificationservice.service.impl;

import com.aeropelican.notificationservice.dto.request.CreateNotificationRequest;
import com.aeropelican.notificationservice.dto.response.NotificationResponse;
import com.aeropelican.notificationservice.entity.Customer;
import com.aeropelican.notificationservice.entity.Notification;
import com.aeropelican.notificationservice.entity.NotificationDelivery;
import com.aeropelican.notificationservice.entity.NotificationTemplate;
import com.aeropelican.notificationservice.entity.NotificationTemplateChannel;
import com.aeropelican.notificationservice.exception.CustomerNotFoundException;
import com.aeropelican.notificationservice.exception.DuplicateEventException;
import com.aeropelican.notificationservice.exception.TemplateNotFoundException;
import com.aeropelican.notificationservice.mapper.NotificationMapper;
import com.aeropelican.notificationservice.repository.CustomerRepository;
import com.aeropelican.notificationservice.repository.NotificationDeliveryRepository;
import com.aeropelican.notificationservice.repository.NotificationRepository;
import com.aeropelican.notificationservice.repository.NotificationTemplateChannelRepository;
import com.aeropelican.notificationservice.repository.NotificationTemplateRepository;
import com.aeropelican.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final CustomerRepository customerRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationTemplateRepository notificationTemplateRepository;
    private final NotificationTemplateChannelRepository notificationTemplateChannelRepository;
    private final NotificationDeliveryRepository notificationDeliveryRepository;
    private final NotificationMapper notificationMapper;

    @Override
    @Transactional
    public NotificationResponse createNotification(
            CreateNotificationRequest request) {

        log.info(
                "Creating notification for eventId: {}",
                request.getEventId()
        );

        // 1. Check duplicate event
        if (notificationRepository
                .findByEventId(request.getEventId())
                .isPresent()) {

            throw new DuplicateEventException(
                    "Notification already exists for eventId: "
                            + request.getEventId()
            );
        }

        // 2. Find customer
        Customer customer = customerRepository
                .findByCustomerCode(request.getCustomerCode())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found: "
                                + request.getCustomerCode()
                ));

        // 3. Find active template
        NotificationTemplate template =
                notificationTemplateRepository
                        .findByEventTypeAndActiveTrue(
                                request.getEventType()
                        )
                        .orElseThrow(() -> new TemplateNotFoundException(
                                "No active template found for event type: "
                                        + request.getEventType()
                        ));

        // 4. Create notification
        Notification notification =
                notificationMapper.toEntity(request, customer);

        notification = notificationRepository.save(notification);

        // 5. Find active template channels
        List<NotificationTemplateChannel> channels =
                notificationTemplateChannelRepository
                        .findByTemplate_IdAndActiveTrue(
                                template.getId()
                        );

        // 6. Create delivery records
        for (NotificationTemplateChannel templateChannel : channels) {

            String recipient = getRecipient(
                    customer,
                    templateChannel.getChannel()
            );

            // Create delivery only when customer is enabled
            // for the particular channel.
            if (recipient != null) {

                NotificationDelivery delivery =
                        NotificationDelivery.builder()
                                .notification(notification)
                                .channel(templateChannel.getChannel())
                                .recipient(recipient)
                                .template(template)
                                .status(
                                        NotificationDelivery.DeliveryStatus
                                                .PENDING
                                )
                                .attemptCount(0)
                                .lastError(null)
                                .scheduledAt(null)
                                .sentAt(null)
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build();

                notificationDeliveryRepository.save(delivery);

                log.info(
                        "Delivery created for notificationId: {}, channel: {}",
                        notification.getId(),
                        templateChannel.getChannel()
                );
            }
        }

        log.info(
                "Notification created successfully. notificationId: {}",
                notification.getId()
        );

        // 7. Return response
        return notificationMapper.toResponse(notification);
    }

    private String getRecipient(
            Customer customer,
            NotificationTemplateChannel.NotificationChannel channel) {

        if (channel ==
                NotificationTemplateChannel.NotificationChannel.EMAIL) {

            if (customer.isEmailEnabled()
                    && customer.getEmail() != null
                    && !customer.getEmail().isBlank()) {

                return customer.getEmail();
            }
        }

        if (channel ==
                NotificationTemplateChannel.NotificationChannel.WHATSAPP) {

            if (customer.isWhatsappEnabled()
                    && customer.getPhoneNumber() != null
                    && !customer.getPhoneNumber().isBlank()) {

                return customer.getPhoneNumber();
            }
        }

        return null;
    }
}