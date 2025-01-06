package com.example.user_service.controller;


import com.example.user_service.dto.request.UserCreateDto;
import com.example.user_service.dto.response.UserResponseDto;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private UserService userService;


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        Optional<User> userOpt = userService.getUserById(userId);

        if(userOpt.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(UserResponseDto.getDto(userOpt.get()));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto dto,
                                        Principal principal){
        User user = userService.createUser(dto, principal.getName());

        return ResponseEntity.ok(UserResponseDto.getDto(user));
    }
}
