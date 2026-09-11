package com.aeropelican.notificationservice.repository;

import com.aeropelican.notificationservice.entity.NotificationDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationDeliveryRepository
        extends JpaRepository<NotificationDelivery, Long> {

    List<NotificationDelivery> findByNotification_Id(Long notificationId);
}