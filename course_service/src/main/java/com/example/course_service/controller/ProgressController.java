package com.example.course_service.controller;

import com.example.course_service.dto.response.CourseProgressResponseDto;
import com.example.course_service.dto.response.CourseResponseDto;
import com.example.course_service.dto.response.ModuleProgressResponseDto;
import com.example.course_service.dto.update.ModuleProgressUpdateDto;
import com.example.course_service.model.Course;
import com.example.course_service.model.CourseProgress;
import com.example.course_service.model.ModuleProgress;
import com.example.course_service.service.CourseProgressService;

import com.example.course_service.service.ModuleProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/api/v1/progress")
public class ProgressController {
    private final CourseProgressService courseProgressService;
    private final ModuleProgressService moduleProgressService;

    public ProgressController(CourseProgressService courseProgressService,
                              ModuleProgressService moduleProgressService) {
        this.courseProgressService = courseProgressService;
        this.moduleProgressService = moduleProgressService;
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getCourseProgress(@PathVariable("courseId") Long courseId) {
        CourseProgress courseProgress = courseProgressService.getCourseProgressById(courseId);

        return ResponseEntity.ok(CourseProgressResponseDto.getDtoWithModules(courseProgress));
    }

    @GetMapping("/course/{courseId}/course")
    public ResponseEntity<?> getCourseByCourseProgressId(@PathVariable("courseId") Long courseProgressId) {

        Course course = courseProgressService.getCourseByCourseProgressId(courseProgressId);
        course.setModules(null);
        return ResponseEntity.ok(
                CourseResponseDto.getDto(course)
        );
    }

    @PostMapping("/course/{courseId}")
    private ResponseEntity<?> createCourseProgress(@RequestHeader("Authorization") String authHeader,
                                                   @PathVariable Long courseId) {
        String token = authHeader.substring(7);

        CourseProgress courseProgress = courseProgressService.createProgressObject(token, courseId);

        return ResponseEntity.ok(courseProgress.getId());

    };



    @GetMapping("/module/{moduleId}")
    private ResponseEntity<?> getModuleById(@PathVariable Long moduleId) {

        ModuleProgress moduleProgress = moduleProgressService.getModuleProgressById(moduleId);

        return ResponseEntity.ok(ModuleProgressResponseDto.getDto(moduleProgress));

    };

    @PutMapping("/module/{moduleId}")
    private ResponseEntity<?> updateModule(@RequestBody ModuleProgressUpdateDto dto,
                                           @PathVariable Long moduleId) {

        ModuleProgress moduleProgress = moduleProgressService.updateModuleProgress(dto, moduleId);

        return ResponseEntity.ok(ModuleProgressResponseDto.getDto(moduleProgress));

    };





}
