package com.example.user_service.controller;

import com.example.user_service.service.UserService;
import com.example.user_service.utils.JwtUtils;
import org.keycloak.jose.jwe.JWE;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/user/courses")
public class CoursesController {
    private final UserService userService;

    public CoursesController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/enroll/{courseId}")
    public Set<Integer> addCourse(@RequestHeader("Authorization") String authHeader,
            @PathVariable Integer courseId){
        JwtUtils jwtUtils = new JwtUtils();

        String token = authHeader.substring(7);


        return null;
    }
}
