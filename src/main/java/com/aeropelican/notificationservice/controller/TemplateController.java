package com.aeropelican.notificationservice.controller;

import com.aeropelican.notificationservice.dto.request.TemplateRequest;
import com.aeropelican.notificationservice.dto.response.TemplateResponse;
import com.aeropelican.notificationservice.service.TemplateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    public ResponseEntity<TemplateResponse> createTemplate(
            @Valid @RequestBody TemplateRequest request) {

        TemplateResponse response = templateService.createTemplate(request);

        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<java.util.List<TemplateResponse>> getAllTemplates() {

        return ResponseEntity.ok(templateService.getAllTemplates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateResponse> getTemplateById(
            @PathVariable Long id) {

        return ResponseEntity.ok(templateService.getTemplateById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemplateResponse> updateTemplate(
            @PathVariable Long id,
            @Valid @RequestBody TemplateRequest request) {

        return ResponseEntity.ok(
                templateService.updateTemplate(id, request)
        );
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<TemplateResponse> updateTemplateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        return ResponseEntity.ok(
                templateService.updateTemplateStatus(id, active)
        );
    }
}