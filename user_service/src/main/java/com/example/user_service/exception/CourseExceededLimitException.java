package com.example.user_service.exception;

public class CourseExceededLimitException extends RuntimeException {
    public CourseExceededLimitException(String message) {
        super(message);
    }
    public CourseExceededLimitException(String message, Throwable cause) {
        super(message, cause);
    }
    public CourseExceededLimitException(Throwable cause) {
        super(cause);
    }
}
