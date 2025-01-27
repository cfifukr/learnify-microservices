package com.example.course_service.exceptions;

public class ModuleNotFoundException extends RuntimeException{

    public ModuleNotFoundException(String message) {
        super(message);
    }
}
