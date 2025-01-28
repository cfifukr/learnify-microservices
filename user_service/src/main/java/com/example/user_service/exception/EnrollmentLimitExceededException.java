package com.example.user_service.exception;

public class EnrollmentLimitExceededException extends RuntimeException {

    public EnrollmentLimitExceededException(String message) {
        super(message);
    }
}