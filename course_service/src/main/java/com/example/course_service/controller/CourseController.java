package com.example.course_service.controller;


import com.example.course_service.dto.PageDto;
import com.example.course_service.dto.create.CourseCreateDto;
import com.example.course_service.dto.response.CourseResponseDto;
import com.example.course_service.exceptions.CourseNotFoundException;
import com.example.course_service.model.Course;
import com.example.course_service.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
        PageDto pageResponse = courseService.getAllCourses(pageable);

        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping("/{courseId}")
    private ResponseEntity<?> getCourseById(@PathVariable Long courseId){

        Optional<Course> courseOpt = courseService.getCourseById(courseId);


        if(courseOpt.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(
                CourseResponseDto.getDto(courseOpt.get()) // optional -> object -> dto
        );


    }

    @PostMapping
    private ResponseEntity<?> createCourse(@RequestBody CourseCreateDto createDto){
        Course course = courseService.saveCourse(createDto);
        return ResponseEntity.ok(course);
    }


}
