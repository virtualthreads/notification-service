package com.aeropelican.notificationservice.dto.response;

import com.aeropelican.notificationservice.entity.NotificationStatus;
import java.time.LocalDateTime;

public class NotificationResponseDTO {

    private Long id;
    private Long customerId;
    private Long templateId;
    private String eventId;
    private String eventType;
    private String payload;
    private NotificationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Default Constructor
    public NotificationResponseDTO() {
    }

    // All-Args Constructor (Must match class name NotificationResponseDTO exactly)
    public NotificationResponseDTO(Long id, Long customerId, Long templateId, String eventId,
                                   String eventType, String payload, NotificationStatus status,
                                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.customerId = customerId;
        this.templateId = templateId;
        this.eventId = eventId;
        this.eventType = eventType;
        this.payload = payload;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}