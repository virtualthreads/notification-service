package com.aeropelican.notificationservice.channel;

import com.aeropelican.notificationservice.entity.NotificationDelivery;
import org.springframework.stereotype.Component;

@Component
public class EmailChannel implements NotificationChannel {

    @Override
    public String getChannelType() {
        return "EMAIL";
    }

    @Override
    public void send(NotificationDelivery delivery) {
        // email sending logic will go here
    }
}
