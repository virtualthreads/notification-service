package com.aeropelican.notificationservice.dto;

import com.aeropelican.notificationservice.channel.ChannelType;

public class NotificationDelivery {

    private String recipient;
    private String message;
    private ChannelType channelType;

    public NotificationDelivery() {
    }

    public NotificationDelivery(
            String recipient,
            String message,
            ChannelType channelType) {

        this.recipient = recipient;
        this.message = message;
        this.channelType = channelType;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }
}