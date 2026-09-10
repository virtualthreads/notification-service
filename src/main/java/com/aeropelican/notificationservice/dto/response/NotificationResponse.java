package com.aeropelican.notificationservice.dto.response;

import com.aeropelican.notificationservice.entity.Notification.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long notificationId;

    private String eventId;

    private String eventType;

    private String customerCode;

    private NotificationStatus status;

    private LocalDateTime createdAt;
}