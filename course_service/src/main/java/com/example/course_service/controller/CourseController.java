package com.example.course_service.controller;


import com.example.course_service.dto.response.CourseResponseDto;
import com.example.course_service.model.Course;
import com.example.course_service.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    private ResponseEntity<?> getAllCourcesPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "25") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseService.getAllCourses(pageable);
        List<CourseResponseDto> listDto = courses.stream()
                .map(CourseResponseDto::getDto)
                .toList();
        return ResponseEntity.ok(listDto);
    }
}
