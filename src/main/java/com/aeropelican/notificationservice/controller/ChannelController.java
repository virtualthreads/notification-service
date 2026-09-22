package com.aeropelican.notificationservice.controller;

import com.aeropelican.notificationservice.channel.NotificationChannel;
import com.aeropelican.notificationservice.service.ChannelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping("/{type}")
    public String getChannel(@PathVariable String type) {

        NotificationChannel channel = channelService.getChannel(type);

        if (channel == null) {
            return "Channel not found: " + type;
        }

        return channel.getChannelType();
    }
}