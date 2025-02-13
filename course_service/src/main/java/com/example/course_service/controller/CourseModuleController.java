package com.example.course_service.controller;

import com.example.course_service.dto.PageDto;
import com.example.course_service.dto.create.CourseModuleCreateDto;
import com.example.course_service.dto.response.CourseModuleResponseDto;
import com.example.course_service.dto.update.CourseModuleUpdateDto;
import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;
import com.example.course_service.service.CourseModuleService;
import com.example.course_service.service.CourseService;
import com.example.course_service.utils.JwtUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/module")
public class CourseModuleController {

    private CourseModuleService courseModuleService;

    public CourseModuleController(CourseModuleService courseModuleService) {
        this.courseModuleService = courseModuleService;

    }

    @GetMapping("/{moduleId}")
    public ResponseEntity<?> getModulesById(@PathVariable Long moduleId){

        CourseModule courseModule = courseModuleService.getModuleById(moduleId);
        return ResponseEntity.ok(CourseModuleResponseDto.getDto(courseModule));
    }

    @PutMapping
    public ResponseEntity<?> updateModule(@RequestBody CourseModuleUpdateDto updateDto,
                                          @RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        CourseModule courseModule = courseModuleService.updateModule(updateDto, token);

        return ResponseEntity.ok(CourseModuleResponseDto.getDto(courseModule));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getModulesForCourse(@PathVariable Long courseId,
                                                 @RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size){
        PageDto pageDto = courseModuleService.getModulesForCourse(courseId, page, size);
        return ResponseEntity.ok(pageDto);
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<?> addModuleForCourse(@RequestHeader("Authorization") String authHeader,
                                                @PathVariable Long courseId,
                                                @RequestBody CourseModuleCreateDto createDto){

        String token = authHeader.substring(7);
        CourseModule courseModule = courseModuleService.createModule(createDto, token, courseId);

        return ResponseEntity.ok(courseModule);
    }

}
