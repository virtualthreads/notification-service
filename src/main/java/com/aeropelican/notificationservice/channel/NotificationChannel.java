package com.aeropelican.notificationservice.channel;

import com.aeropelican.notificationservice.entity.NotificationDelivery;


public interface NotificationChannel {
    String getChannelType();
    void send(NotificationDelivery delivery);
}
