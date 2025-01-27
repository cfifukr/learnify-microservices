package com.example.course_service.exceptions;

public class WrongUserException extends RuntimeException{

    public WrongUserException(String message) {
        super(message);
    }
}
