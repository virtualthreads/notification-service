package com.aeropelican.notificationservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private Long customerId;

    private Long notificationTemplateId;

    private String status;

    public Notification() {
    }

    public Notification(
            Long notificationId,
            Long customerId,
            Long notificationTemplateId,
            String status) {

        this.notificationId = notificationId;
        this.customerId = customerId;
        this.notificationTemplateId = notificationTemplateId;
        this.status = status;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getNotificationTemplateId() {
        return notificationTemplateId;
    }

    public void setNotificationTemplateId(Long notificationTemplateId) {
        this.notificationTemplateId = notificationTemplateId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}