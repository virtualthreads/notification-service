package com.aeropelican.notificationservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class UpdateTemplateRequestDTO {
    @NotBlank(message = "Template name cannot be blank")
    private String name;

    private String description;
    private List<ChannelContentDTO> channels;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<ChannelContentDTO> getChannels() { return channels; }
    public void setChannels(List<ChannelContentDTO> channels) { this.channels = channels; }

    public static class ChannelContentDTO {
        @NotBlank(message = "Channel type is required")
        private String channelType;
        private String content;

        public String getChannelType() { return channelType; }
        public void setChannelType(String channelType) { this.channelType = channelType; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }
}