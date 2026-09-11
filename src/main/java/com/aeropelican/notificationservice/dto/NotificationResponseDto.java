package com.aeropelican.notificationservice.dto;

import java.time.LocalDateTime;

public class NotificationResponseDto {

    private Long id;
    private String eventId;
    private String eventType;
    private Long customerId;
    private String payload;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public NotificationResponseDto() {
    }

    public NotificationResponseDto(
            Long id,
            String eventId,
            String eventType,
            Long customerId,
            String payload,
            String status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        this.id = id;
        this.eventId = eventId;
        this.eventType = eventType;
        this.customerId = customerId;
        this.payload = payload;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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