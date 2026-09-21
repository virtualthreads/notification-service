package com.aeropelican.notificationservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long notificationId;
    private Long customerId;
    private Long notificationTemplateId;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private String eventId;
    private String eventType;
    private String payload;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters
    public Long getId() { return id; }
    public Long getNotificationId() { return notificationId != null ? notificationId : id; }
    public Long getCustomerId() { return customerId; }
    public Long getNotificationTemplateId() { return notificationTemplateId; }
    public NotificationStatus getStatus() { return status; }
    public String getEventId() { return eventId; }
    public String getEventType() { return eventType; }
    public String getPayload() { return payload; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNotificationId(Long notificationId) { this.notificationId = notificationId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setNotificationTemplateId(Long notificationTemplateId) { this.notificationTemplateId = notificationTemplateId; }
    public void setStatus(NotificationStatus status) { this.status = status; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public void setPayload(String payload) { this.payload = payload; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}