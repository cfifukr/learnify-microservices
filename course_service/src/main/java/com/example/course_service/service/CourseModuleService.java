package com.example.course_service.service;

import com.example.course_service.dto.PageDto;
import com.example.course_service.dto.create.CourseModuleCreateDto;
import com.example.course_service.dto.response.CourseModuleResponseDto;
import com.example.course_service.dto.response.CourseResponseDto;
import com.example.course_service.dto.update.CourseModuleUpdateDto;
import com.example.course_service.exceptions.CourseNotFoundException;
import com.example.course_service.exceptions.ModuleNotFoundException;
import com.example.course_service.exceptions.WrongUserException;
import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;
import com.example.course_service.repository.CourseModuleRepository;
import com.example.course_service.repository.CourseRepository;
import com.example.course_service.utils.JwtUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseModuleService {

    private CourseModuleRepository courseModuleRepository;
    private CourseRepository courseRepository;
    private JwtUtils jwtUtils;


    public CourseModuleService(CourseModuleRepository courseModuleRepository,
                               CourseRepository courseRepository,
                               JwtUtils jwtUtils) {
        this.courseModuleRepository = courseModuleRepository;
        this.courseRepository = courseRepository;
        this.jwtUtils = jwtUtils;
    }



    public PageDto getModulesForCourse(Long courseId, int number,  int size){
        Pageable pageable = PageRequest.of(number, size);
        Page<CourseModule> page = courseModuleRepository.getCourseModulesByCourseId(courseId, pageable);
        PageDto pageDto = PageDto.getDto(page, page.get().map(CourseModuleResponseDto::getDto).toList());

        return pageDto;
    }

    public CourseModule createModule(CourseModuleCreateDto createDto,
                                     String token,
                                     Long courseId) {

        String creatorId = jwtUtils.extractKeycloakId(token);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseId));

        if(!course.getCreatorId().equals(creatorId)){
            throw new WrongUserException("User aren`t allowed to perform this action to this course");
        }

        CourseModule courseModule = createDto.getCourseModule(course);

        course.getModules().add(courseModule);
        return courseModuleRepository.save(courseModule);
    }

    public CourseModule updateModule(CourseModuleUpdateDto updateDto, String token){
        String creatorId = jwtUtils.extractKeycloakId(token);
        Course course = courseRepository.findById(updateDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + updateDto.getCourseId()));

        CourseModule courseModuleOld = courseModuleRepository.findById(updateDto.getId())
                .orElseThrow(() -> new ModuleNotFoundException("Module not found with id " + updateDto.getId()));

        if(!course.getCreatorId().equals(creatorId)){
            throw new WrongUserException("User aren`t allowed to perform this action to this course");
        }

        //TODO: Need to check order of modules, it must be one by one and no duplications
        CourseModule courseModuleNew = updateDto.updateCourseDto(courseModuleOld);

        return courseModuleRepository.save(courseModuleNew);

    }


    public CourseModule getModuleById(Long id){
        return courseModuleRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
    }



}
