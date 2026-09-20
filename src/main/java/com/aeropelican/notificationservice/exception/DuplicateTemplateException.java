package com.aeropelican.notificationservice.exception;

public class DuplicateTemplateException extends RuntimeException {

    public DuplicateTemplateException(String message) {
        super(message);
    }
}