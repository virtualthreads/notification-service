package com.aeropelican.notificationservice.controller;

import com.aeropelican.notificationservice.dto.response.RenderedTemplateResponse;
import com.aeropelican.notificationservice.service.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

    @RestController
    @RequestMapping("/api/v1/templates")
    public class TemplateController {

        private final TemplateService templateService;

        public TemplateController(TemplateService templateService) {
            this.templateService = templateService;
        }

        @PostMapping("/{templateCode}/render")
        public ResponseEntity<RenderedTemplateResponse> renderTemplate(
                @PathVariable String templateCode,
                @RequestParam String channel,
                @RequestBody Map<String, Object> data) {

            RenderedTemplateResponse response =
                    templateService.renderTemplate(
                            templateCode,
                            channel,
                            data
                    );

            return ResponseEntity.ok(response);
        }

}
