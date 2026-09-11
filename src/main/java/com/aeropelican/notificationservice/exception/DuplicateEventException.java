package com.aeropelican.notificationservice.exception;

public class DuplicateEventException extends RuntimeException {

    public DuplicateEventException(String message) {
        super(message);
    }
}