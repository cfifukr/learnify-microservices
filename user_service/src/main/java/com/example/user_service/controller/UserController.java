package com.example.user_service.controller;


import com.example.user_service.dto.request.UserCreateDto;
import com.example.user_service.dto.request.UserLoginDto;
import com.example.user_service.dto.response.TokenResponse;
import com.example.user_service.dto.response.UserResponseDto;
import com.example.user_service.model.User;
import com.example.user_service.service.KeycloakService;
import com.example.user_service.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;


@Validated
@Slf4j
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final  UserService userService;
    private final KeycloakService keycloakService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(UserService userService,
                          KeycloakService keycloakService){
        this.userService = userService;
        this.keycloakService = keycloakService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable @NotNull Long userId){
        User user = userService.getUserById(userId);

        return ResponseEntity.ok(UserResponseDto.getDto(user));
    }


    @GetMapping("/keycloak/{keycloakId}")
    public ResponseEntity<?> getUserByKeycloakId(@PathVariable @NotNull String keycloakId){
        User user = userService.getUserByKeycloakId(keycloakId);

        return ResponseEntity.ok(UserResponseDto.getDto(user));
    }



    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserCreateDto request) {


        String keycloakId =  keycloakService.registerUser(
                request.getUsername(),
                request.getEmail(),
                request.getName(),
                request.getSurname(),
                request.getPassword()
        );

        User user = userService.createUser(request, keycloakId);

        return ResponseEntity.ok(UserResponseDto.getDto(user));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto request,
                                   BindingResult result, HttpServletResponse httpServletResponse) {
        log.info("Login request by: {}", request.getUsername());



        try {
            TokenResponse tokenResponse = keycloakService.getToken(request.getUsername(), request.getPassword());
            log.info("User {} logged in", request.getUsername());
            return ResponseEntity.ok(tokenResponse);

        }catch (HttpClientErrorException exception){

            log.warn("User {} couldn`t login", request.getUsername());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");

        }

    }


}
