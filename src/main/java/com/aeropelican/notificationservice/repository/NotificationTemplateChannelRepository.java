package com.aeropelican.notificationservice.repository;

import com.aeropelican.notificationservice.entity.NotificationTemplateChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationTemplateChannelRepository
        extends JpaRepository<NotificationTemplateChannel, Long> {

    List<NotificationTemplateChannel> findByTemplate_IdAndActiveTrue(Long templateId);
}