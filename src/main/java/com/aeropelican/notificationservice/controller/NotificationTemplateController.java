package com.aeropelican.notificationservice.controller;

import com.aeropelican.notificationservice.dto.request.UpdateTemplateRequestDTO;
import com.aeropelican.notificationservice.dto.request.UpdateTemplateStatusRequestDTO;
import com.aeropelican.notificationservice.dto.response.TemplateResponseDTO;
import com.aeropelican.notificationservice.service.NotificationTemplateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/templates")
public class NotificationTemplateController {

    private final NotificationTemplateService templateService;

    public NotificationTemplateController(NotificationTemplateService templateService) {
        this.templateService = templateService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemplateResponseDTO> updateTemplate(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTemplateRequestDTO request) {
        return ResponseEntity.ok(templateService.updateTemplate(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TemplateResponseDTO> updateTemplateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTemplateStatusRequestDTO request) {
        return ResponseEntity.ok(templateService.updateTemplateStatus(id, request));
    }
}