package com.aeropelican.notificationservice.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    @Component
    public class TemplateRenderer {

        private static final Pattern PLACEHOLDER_PATTERN =
                Pattern.compile("\\{\\{\\s*([^{}]+?)\\s*\\}\\}");

        /**
         * Replaces placeholders in a template with values from the data map.
         *
         * Example:
         * Template: "Hi {{customerName}}, your order {{orderId}} is confirmed."
         *
         * Data:
         * customerName = Rahul
         * orderId = ORD-1001
         *
         * Result:
         * "Hi Rahul, your order ORD-1001 is confirmed."
         */
        public String render(String template, Map<String, Object> data) {

            if (template == null || template.isBlank()) {
                return template;
            }

            if (data == null) {
                data = Map.of();
            }

            Matcher matcher = PLACEHOLDER_PATTERN.matcher(template);
            StringBuffer result = new StringBuffer();

            while (matcher.find()) {

                String placeholder = matcher.group(1).trim();

                Object value = data.get(placeholder);

                if (value == null) {
                    // Keep missing placeholders unchanged
                    matcher.appendReplacement(
                            result,
                            Matcher.quoteReplacement(matcher.group(0))
                    );
                } else {
                    matcher.appendReplacement(
                            result,
                            Matcher.quoteReplacement(String.valueOf(value))
                    );
                }
            }

            matcher.appendTail(result);

            return result.toString();
        }
    
}
