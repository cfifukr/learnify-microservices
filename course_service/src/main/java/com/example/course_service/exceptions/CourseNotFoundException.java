package com.example.course_service.exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException() {
    }

    public CourseNotFoundException(String message) {
        super(message);
    }
}
