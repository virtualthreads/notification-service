package com.aeropelican.notificationservice.service.impl;

import com.aeropelican.notificationservice.channel.ChannelType;
import com.aeropelican.notificationservice.channel.NotificationChannel;
import com.aeropelican.notificationservice.dto.NotificationDelivery;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationChannel implements NotificationChannel {

    @Override
    public ChannelType getChannelType() {
        return ChannelType.EMAIL;
    }

    @Override
    public void send(NotificationDelivery delivery) {
        System.out.println(
                "[EMAIL CHANNEL] Sending Email to: "
                        + delivery.getRecipient()
                        + " | Content: "
                        + delivery.getMessage()
        );
    }
}