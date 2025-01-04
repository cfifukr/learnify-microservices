package com.example.course_service.service;

import com.example.course_service.dto.PageDto;
import com.example.course_service.dto.create.CourseModuleCreateDto;
import com.example.course_service.dto.response.CourseModuleResponseDto;
import com.example.course_service.dto.response.CourseResponseDto;
import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;
import com.example.course_service.repository.CourseModuleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseModuleService {

    private CourseModuleRepository courseModuleRepository;


    public CourseModuleService(CourseModuleRepository courseModuleRepository){
        this.courseModuleRepository = courseModuleRepository;
    }



    public PageDto getModulesForCourse(Long courseId, Pageable pageable){
        Page<CourseModule> page = courseModuleRepository.getCourseModulesByCourseId(courseId, pageable);
        PageDto pageDto = PageDto.getDto(page, page.get().map(CourseModuleResponseDto::getDto).toList());

        return pageDto;
    }

    public CourseModule createModule(CourseModuleCreateDto createDto, Course course){
            CourseModule courseModule = createDto.getCourseModule(course);

        return courseModuleRepository.save(courseModule);
    }


}
