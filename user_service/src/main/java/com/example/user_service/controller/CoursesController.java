package com.example.user_service.controller;

import com.example.user_service.service.UserCourseService;
import com.example.user_service.service.UserService;
import com.example.user_service.utils.JwtUtils;
import org.keycloak.jose.jwe.JWE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/user/course")
public class CoursesController {
    private final UserService userService;
    private final UserCourseService userCourseService;

    public CoursesController(UserService userService,
                             UserCourseService userCourseService) {
        this.userService = userService;
        this.userCourseService = userCourseService;
    }

    @PostMapping("/enroll/{courseId}")
    public ResponseEntity<?> addCourse(@RequestHeader("Authorization") String authHeader,
                                    @PathVariable String courseId){

        String token = authHeader.substring(7);

        List<String> coursesIdsList = userCourseService.enrollUserForCourse(token, courseId);

        return ResponseEntity.ok(coursesIdsList);
    }
}
