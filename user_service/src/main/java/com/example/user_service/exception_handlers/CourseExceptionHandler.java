package com.example.user_service.exception_handlers;

import com.example.user_service.controller.CoursesController;
import com.example.user_service.exception.CourseExceededLimitException;
import com.example.user_service.exception.KeycloakException;
import com.example.user_service.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CoursesController.class)
@Order(2)
public class CourseExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(CourseExceptionHandler.class);


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {

        logger.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(KeycloakException.class)
    public ResponseEntity<String> handleKeycloakException(KeycloakException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseExceededLimitException.class)
    private ResponseEntity<String> handleCourseExceededLimitException(CourseExceededLimitException e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>("The limit of courses is exceeded", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
