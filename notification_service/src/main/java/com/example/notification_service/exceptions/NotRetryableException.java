package com.example.notification_service.exceptions;

public class NotRetryableException extends RuntimeException {
    public NotRetryableException(String message) {
        super(message);
    }

    public NotRetryableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotRetryableException(Throwable cause) {
        super(cause);
    }
}
