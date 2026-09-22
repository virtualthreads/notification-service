package com.aeropelican.notificationservice.channel;

import com.aeropelican.notificationservice.entity.NotificationDelivery;
import org.springframework.stereotype.Component;

@Component
public class WhatsAppChannel implements NotificationChannel {

    @Override
    public String getChannelType() {
        return "WHATSAPP";
    }

    @Override
    public void send(NotificationDelivery delivery) {
        // WhatsApp sending logic will go here
    }
}