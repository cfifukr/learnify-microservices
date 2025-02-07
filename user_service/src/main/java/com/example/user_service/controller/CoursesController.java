package com.example.user_service.controller;

import com.example.user_service.service.UserCourseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/course")
public class CoursesController {
    private final UserCourseService userCourseService;

    public CoursesController(UserCourseService userCourseService) {
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
