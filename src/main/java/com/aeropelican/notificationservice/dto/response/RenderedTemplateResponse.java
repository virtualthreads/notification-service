package com.aeropelican.notificationservice.dto.response;

public record RenderedTemplateResponse(
        String templateCode,
        String eventType,
        String channel,
        String subject,
        String body
) {
}

