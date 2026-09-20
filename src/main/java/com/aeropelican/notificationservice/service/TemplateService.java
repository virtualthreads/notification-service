package com.aeropelican.notificationservice.service;

import com.aeropelican.notificationservice.dto.request.TemplateRequest;
import com.aeropelican.notificationservice.dto.response.TemplateResponse;

import java.util.List;

public interface TemplateService {

    TemplateResponse createTemplate(TemplateRequest request);

    List<TemplateResponse> getAllTemplates();

    TemplateResponse getTemplateById(Long id);

    TemplateResponse updateTemplate(Long id, TemplateRequest request);

    TemplateResponse updateTemplateStatus(Long id, boolean active);
}