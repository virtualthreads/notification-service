package com.aeropelican.notificationservice.channel;

import com.aeropelican.notificationservice.dto.NotificationDelivery;

public interface NotificationChannel {

    ChannelType getChannelType();

    void send(NotificationDelivery delivery);
}