package com.aeropelican.notificationservice.service;

import com.aeropelican.notificationservice.channel.ChannelRegistry;
import com.aeropelican.notificationservice.channel.NotificationChannel;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {

    private final ChannelRegistry channelRegistry;

    public ChannelService(ChannelRegistry channelRegistry) {
        this.channelRegistry = channelRegistry;
    }

    public NotificationChannel getChannel(String channelType) {
        return channelRegistry.getChannel(channelType);
    }
}
