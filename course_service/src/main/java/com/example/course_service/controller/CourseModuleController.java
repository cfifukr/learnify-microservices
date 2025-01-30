package com.example.course_service.controller;

import com.example.course_service.dto.PageDto;
import com.example.course_service.dto.create.CourseModuleCreateDto;
import com.example.course_service.dto.response.CourseModuleResponseDto;
import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;
import com.example.course_service.service.CourseModuleService;
import com.example.course_service.service.CourseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/module")
public class CourseModuleController {

    private CourseModuleService courseModuleService;
    private CourseService courseService;


    public CourseModuleController(CourseModuleService courseModuleService){
        this.courseModuleService = courseModuleService;
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getModulesForCourse(@PathVariable Long courseId,
                                                       @RequestParam(defaultValue = "0") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        PageDto pageDto = courseModuleService.getModulesForCourse(courseId, pageable);
        return ResponseEntity.ok(pageDto);

    }

    @GetMapping("/{moduleId}")
    public ResponseEntity<?> getModulesById(@PathVariable Long moduleId){
        CourseModule courseModule = courseModuleService.getModuleById(moduleId);
        return ResponseEntity.ok(CourseModuleResponseDto.getDto(courseModule));
    }



    @PostMapping("/course/{courseId}")
    public ResponseEntity<?> addModuleForCourse(@PathVariable Long courseId,
                                                @RequestBody CourseModuleCreateDto createDto){

        Course course = courseService.getCourseById(courseId);


        CourseModule courseModule = courseModuleService.createModule(createDto, course);

        return ResponseEntity.ok(courseModule);

    }

}
