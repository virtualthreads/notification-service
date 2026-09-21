package com.aeropelican.notificationservice.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TemplateRenderer {

    private static final Pattern PLACEHOLDER = Pattern.compile("\\{\\{([^}]+)}}");
    public String render(String template,
        Map<String, Object> parameters) {
        if (template == null) {
            return "";
        }

        Matcher matcher = PLACEHOLDER.matcher(template);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {

            String key = matcher.group(1).trim();

            if (!parameters.containsKey(key) || parameters.get(key) == null) {
                throw new IllegalArgumentException(
                        "Missing template parameter: " + key
                );
            }

            String value =
                    String.valueOf(parameters.get(key));

            matcher.appendReplacement(
                    result,
                    Matcher.quoteReplacement(value)
            );
        }

        matcher.appendTail(result);
        return result.toString();
    }
}
