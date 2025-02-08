package com.example.statistic_service.exception;

public class StatNotFoundException extends RuntimeException {
    public StatNotFoundException(String message) {
        super(message);
    }
}
