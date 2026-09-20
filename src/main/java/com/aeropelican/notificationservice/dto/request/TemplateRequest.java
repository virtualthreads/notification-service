package com.aeropelican.notificationservice.dto.request;

import com.aeropelican.notificationservice.entity.NotificationTemplateChannel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRequest {

    @NotBlank(message = "Template code is required")
    @Size(max = 100, message = "Template code must not exceed 100 characters")
    private String templateCode;

    @NotBlank(message = "Event type is required")
    @Size(max = 100, message = "Event type must not exceed 100 characters")
    private String eventType;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @NotEmpty(message = "At least one channel is required")
    @Valid
    private List<TemplateChannelRequest> channels;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TemplateChannelRequest {

        private NotificationTemplateChannel.NotificationChannel channel;

        private String subject;

        @NotBlank(message = "Template body is required")
        private String body;
    }
}