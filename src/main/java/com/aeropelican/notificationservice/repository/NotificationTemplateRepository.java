package com.aeropelican.notificationservice.repository;

import com.aeropelican.notificationservice.entity.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationTemplateRepository
        extends JpaRepository<NotificationTemplate, Long> {

    Optional<NotificationTemplate> findByTemplateCode(String templateCode);

    Optional<NotificationTemplate> findByEventTypeAndActiveTrue(String eventType);
}