package com.aeropelican.notificationservice.dispatcher;

import com.aeropelican.notificationservice.channel.ChannelType;
import com.aeropelican.notificationservice.channel.NotificationChannel;
import com.aeropelican.notificationservice.dto.NotificationDelivery;
import com.aeropelican.notificationservice.exception.UnsupportedChannelException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class NotificationDispatcher {

    private final Map<ChannelType, NotificationChannel> channelMap;

    public NotificationDispatcher(List<NotificationChannel> channels) {
        this.channelMap = channels.stream()
                .collect(Collectors.toMap(
                        NotificationChannel::getChannelType,
                        Function.identity()
                ));
    }

    public void dispatch(NotificationDelivery delivery) {

        if (delivery == null || delivery.getChannelType() == null) {
            throw new IllegalArgumentException(
                    "Notification delivery request or channel type cannot be null"
            );
        }

        NotificationChannel channel =
                channelMap.get(delivery.getChannelType());

        if (channel == null) {
            throw new UnsupportedChannelException(
                    "No channel implementation found for: "
                            + delivery.getChannelType()
            );
        }

        channel.send(delivery);
    }
}