package com.example.course_service.controller;

import com.example.course_service.dto.response.CourseProgressResponseDto;
import com.example.course_service.model.CourseProgress;
import com.example.course_service.service.CourseProgressService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/progress")
public class ProgressController {
    private final CourseProgressService courseProgressService;

    public ProgressController(CourseProgressService courseProgressService) {
        this.courseProgressService = courseProgressService;
    }

    @PostMapping("/course/{courseId}")
    private ResponseEntity<?> createCourseProgress(@RequestHeader("Authorization") String authHeader,
                                                   @PathVariable Long courseId) {
        String token = authHeader.substring(7);

        CourseProgress courseProgress = courseProgressService.createProgressObject(token, courseId);

        return ResponseEntity.ok(CourseProgressResponseDto.getDtoWithModules(courseProgress));

    };
}
