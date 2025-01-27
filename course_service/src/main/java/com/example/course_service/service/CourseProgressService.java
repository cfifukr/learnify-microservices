package com.example.course_service.service;

import com.example.course_service.exceptions.CourseNotFoundException;
import com.example.course_service.exceptions.ModuleNotFoundException;
import com.example.course_service.exceptions.WrongUserException;
import com.example.course_service.model.Course;
import com.example.course_service.model.CourseProgress;
import com.example.course_service.model.ModuleProgress;
import com.example.course_service.repository.CourseProgressRepository;
import com.example.course_service.repository.ModuleProgressRepository;
import com.example.course_service.utils.CourseProgressBuilder;
import com.example.course_service.utils.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CourseProgressService {
    private final CourseProgressRepository courseProgressRepository;
    private final ModuleProgressRepository moduleProgressRepository;
    private final CourseService courseService;

    public CourseProgressService(CourseProgressRepository courseProgressRepository,
                                 ModuleProgressRepository moduleProgressRepository,
                                 CourseService courseService) {
        this.courseProgressRepository = courseProgressRepository;
        this.courseService = courseService;
        this.moduleProgressRepository = moduleProgressRepository;
    }

    public CourseProgress createProgressObject(String token,
                                     Long courseId){

        String keycloakId = new JwtUtils().extractKeycloakId(token);
        Course course = courseService.getCourseById(courseId);
        CourseProgress courseProgress = CourseProgressBuilder.buildCourseProgress(course, keycloakId);

        courseProgressRepository.save(courseProgress);

        course.getModules().stream().forEach(module -> {
            ModuleProgress moduleProgress = CourseProgressBuilder
                    .buildModuleProgress(module, keycloakId, courseProgress);
            moduleProgressRepository.save(moduleProgress);

            courseProgress.addModule(moduleProgress);
        });

        return  courseProgressRepository.save(courseProgress);

    }

    public CourseProgress getCourseProgressById(Long courseProgressId) {
        return courseProgressRepository.findById(courseProgressId)
                .orElseThrow(() -> new CourseNotFoundException("CourseProgress with id " + courseProgressId + " not found"));
    }



}
