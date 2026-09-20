package com.aeropelican.notificationservice.service.impl;

import com.aeropelican.notificationservice.repository.NotificationTemplateRepository;
import com.aeropelican.notificationservice.entity.NotificationTemplate;
import com.aeropelican.notificationservice.entity.NotificationTemplateChannel;
import com.aeropelican.notificationservice.repository.NotificationTemplateChannelRepository;
import org.springframework.transaction.annotation.Transactional;
import com.aeropelican.notificationservice.exception.TemplateNotFoundException;

import com.aeropelican.notificationservice.dto.request.TemplateRequest;
import com.aeropelican.notificationservice.dto.response.TemplateResponse;
import com.aeropelican.notificationservice.service.TemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final NotificationTemplateRepository notificationTemplateRepository;

    private final NotificationTemplateChannelRepository notificationTemplateChannelRepository;

    public TemplateServiceImpl(
            NotificationTemplateRepository notificationTemplateRepository,
            NotificationTemplateChannelRepository notificationTemplateChannelRepository) {

        this.notificationTemplateRepository = notificationTemplateRepository;
        this.notificationTemplateChannelRepository = notificationTemplateChannelRepository;
    }

    @Transactional
    @Override
    public TemplateResponse createTemplate(TemplateRequest request) {

        NotificationTemplate template = NotificationTemplate.builder()
                .templateCode(request.getTemplateCode())
                .eventType(request.getEventType())
                .description(request.getDescription())
                .active(true)
                .build();

        template = notificationTemplateRepository.save(template);

        for (TemplateRequest.TemplateChannelRequest channelRequest : request.getChannels()) {

            NotificationTemplateChannel channel = NotificationTemplateChannel.builder()
                    .template(template)
                    .channel(channelRequest.getChannel())
                    .subject(channelRequest.getSubject())
                    .body(channelRequest.getBody())
                    .active(true)
                    .build();

            notificationTemplateChannelRepository.save(channel);
        }

        return TemplateResponse.builder()
                .id(template.getId())
                .templateCode(template.getTemplateCode())
                .eventType(template.getEventType())
                .description(template.getDescription())
                .active(template.isActive())
                .createdAt(template.getCreatedAt())
                .updatedAt(template.getUpdatedAt())
                .build();
    }

    @Override
    public List<TemplateResponse> getAllTemplates() {

        return notificationTemplateRepository.findAll()
                .stream()
                .map(template -> TemplateResponse.builder()
                        .id(template.getId())
                        .templateCode(template.getTemplateCode())
                        .eventType(template.getEventType())
                        .description(template.getDescription())
                        .active(template.isActive())
                        .createdAt(template.getCreatedAt())
                        .updatedAt(template.getUpdatedAt())
                        .build())
                .toList();
    }

    @Override
    public TemplateResponse getTemplateById(Long id) {

        NotificationTemplate template = notificationTemplateRepository.findById(id)
                .orElseThrow(() ->
                        new TemplateNotFoundException(
                                "Template not found with id: " + id
                        )
                );

        return TemplateResponse.builder()
                .id(template.getId())
                .templateCode(template.getTemplateCode())
                .eventType(template.getEventType())
                .description(template.getDescription())
                .active(template.isActive())
                .createdAt(template.getCreatedAt())
                .updatedAt(template.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional
    public TemplateResponse updateTemplate(Long id, TemplateRequest request) {

        NotificationTemplate template = notificationTemplateRepository.findById(id)
                .orElseThrow(() ->
                        new TemplateNotFoundException(
                                "Template not found with id: " + id
                        )
                );

        template.setTemplateCode(request.getTemplateCode());
        template.setEventType(request.getEventType());
        template.setDescription(request.getDescription());

        template = notificationTemplateRepository.save(template);

        return TemplateResponse.builder()
                .id(template.getId())
                .templateCode(template.getTemplateCode())
                .eventType(template.getEventType())
                .description(template.getDescription())
                .active(template.isActive())
                .createdAt(template.getCreatedAt())
                .updatedAt(template.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional
    public TemplateResponse updateTemplateStatus(Long id, boolean active) {

        NotificationTemplate template = notificationTemplateRepository.findById(id)
                .orElseThrow(() ->
                        new TemplateNotFoundException(
                                "Template not found with id: " + id
                        )
                );

        template.setActive(active);

        template = notificationTemplateRepository.save(template);

        return TemplateResponse.builder()
                .id(template.getId())
                .templateCode(template.getTemplateCode())
                .eventType(template.getEventType())
                .description(template.getDescription())
                .active(template.isActive())
                .createdAt(template.getCreatedAt())
                .updatedAt(template.getUpdatedAt())
                .build();
    }
}