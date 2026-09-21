package com.aeropelican.notificationservice.service.impl;
import com.aeropelican.notificationservice.dto.response.RenderedTemplateResponse;
import com.aeropelican.notificationservice.entity.NotificationTemplate;
import com.aeropelican.notificationservice.entity.NotificationTemplateChannel;
import com.aeropelican.notificationservice.exception.TemplateNotFoundException;
import com.aeropelican.notificationservice.repository.NotificationTemplateChannelRepository;
import com.aeropelican.notificationservice.repository.NotificationTemplateRepository;
import com.aeropelican.notificationservice.service.TemplateRenderer;
import com.aeropelican.notificationservice.service.TemplateService;
import org.springframework.stereotype.Service;

import java.util.Map;

    @Service
    public class TemplateServiceImpl implements TemplateService {

        private final NotificationTemplateRepository templateRepository;
        private final NotificationTemplateChannelRepository channelRepository;
        private final TemplateRenderer templateRenderer;

        public TemplateServiceImpl(
                NotificationTemplateRepository templateRepository,
                NotificationTemplateChannelRepository channelRepository,
                TemplateRenderer templateRenderer) {

            this.templateRepository = templateRepository;
            this.channelRepository = channelRepository;
            this.templateRenderer = templateRenderer;
        }

        @Override
        public RenderedTemplateResponse renderTemplate(
                String templateCode,
                String channel,
                Map<String, Object> data) {

            // 1. Find active template
            NotificationTemplate template =
                    templateRepository.findByTemplateCode(templateCode)
                            .filter(NotificationTemplate::isActive)
                            .orElseThrow(() ->
                                    new TemplateNotFoundException(
                                            "Active template not found: " + templateCode
                                    )
                            );

            // 2. Convert channel string to enum
            NotificationTemplateChannel.NotificationChannel notificationChannel;

            try {
                notificationChannel =
                        NotificationTemplateChannel.NotificationChannel
                                .valueOf(channel.toUpperCase());
            } catch (IllegalArgumentException ex) {

                throw new IllegalArgumentException(
                        "Unsupported notification channel: " + channel
                );
            }

            // 3. Find channel-specific template
            NotificationTemplateChannel templateChannel =
                    channelRepository
                            .findByTemplateIdAndChannelAndActiveTrue(
                                    template.getId(),
                                    notificationChannel
                            )
                            .orElseThrow(() ->
                                    new TemplateNotFoundException(
                                            "Active template channel not found for template: "
                                                    + templateCode
                                                    + ", channel: "
                                                    + channel
                                    )
                            );

            // 4. Render subject
            String renderedSubject =
                    templateRenderer.render(
                            templateChannel.getSubject(),
                            data
                    );

            // 5. Render body
            String renderedBody =
                    templateRenderer.render(
                            templateChannel.getBody(),
                            data
                    );

            // 6. Return rendered template
            return new RenderedTemplateResponse(
                    template.getTemplateCode(),
                    template.getEventType(),
                    templateChannel.getChannel().name(),
                    renderedSubject,
                    renderedBody
            );
        }

}
