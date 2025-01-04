package com.example.user_service.exception;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
