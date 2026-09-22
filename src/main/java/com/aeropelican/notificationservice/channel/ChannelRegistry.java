package com.aeropelican.notificationservice.channel;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ChannelRegistry {

    private final Map<String, NotificationChannel> channels;

    public ChannelRegistry(List<NotificationChannel> channelList) {
        this.channels = channelList.stream()
                .collect(Collectors.toMap(
                        NotificationChannel::getChannelType,
                        Function.identity()
                ));
    }

    public NotificationChannel getChannel(String channelType) {
        return channels.get(channelType);
    }
}
