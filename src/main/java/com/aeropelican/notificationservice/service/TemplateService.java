package com.aeropelican.notificationservice.service;

import com.aeropelican.notificationservice.dto.response.RenderedTemplateResponse;
import java.util.Map;

    public interface TemplateService {

        RenderedTemplateResponse renderTemplate(
                String templateCode,
                String channel,
                Map<String, Object> data
        );
    
}
