package com.example.statistic_service.controllers;

import com.example.statistic_service.model.CourseStat;
import com.example.statistic_service.service.CourseStatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/statistic/course")
public class CourseStatisticController {
    private final CourseStatService courseStatService;
    public CourseStatisticController(CourseStatService courseStatService) {
        this.courseStatService = courseStatService;
    }


    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseStatistic(@PathVariable Long courseId) {
        CourseStat courseStat = courseStatService.getCourseStatByCourseId(courseId);
        return new ResponseEntity<>(courseStat, HttpStatus.OK);
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<?> getOrCreateCourseStatistic(@PathVariable Long courseId) {
        CourseStat courseStat = courseStatService.getCourseStatByCourseId(courseId);
        return new ResponseEntity<>(courseStat, HttpStatus.OK);
    }


}
