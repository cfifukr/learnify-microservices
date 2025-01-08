package com.example.user_service.controller;


import com.example.user_service.dto.request.UserCreateDto;
import com.example.user_service.dto.response.UserResponseDto;
import com.example.user_service.model.User;
import com.example.user_service.service.KeycloakService;
import com.example.user_service.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private UserService userService;
    private KeycloakService keycloakService;

    public UserController(UserService userService,
                          KeycloakService keycloakService){
        this.userService = userService;
        this.keycloakService = keycloakService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        Optional<User> userOpt = userService.getUserById(userId);

        if(userOpt.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(UserResponseDto.getDto(userOpt.get()));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreateDto request) {
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

}
