package com.example.course_service.service;

import com.example.course_service.dto.PageDto;
import com.example.course_service.dto.create.CourseCreateDto;
import com.example.course_service.dto.response.CourseResponseDto;
import com.example.course_service.dto.update.CourseUpdateDto;
import com.example.course_service.exceptions.CourseNotFoundException;
import com.example.course_service.exceptions.WrongUserException;
import com.example.course_service.model.Course;
import com.example.course_service.repository.CourseRepository;
import com.example.course_service.utils.JwtUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final JwtUtils jwtUtils = new JwtUtils();

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }


    public PageDto getAllCourses(Pageable pageable){

        Page<Course> page =  courseRepository.findAll(pageable);

        PageDto pageDto = new PageDto(pageable.getPageNumber(),
                pageable.getPageSize(),
                page.getTotalElements(),
                page.get().map(CourseResponseDto::getDto).toList());


        return pageDto;
    }


    public Course getCourseById(Long id) throws CourseNotFoundException{
        return  courseRepository.findById(id)
                .orElseThrow(()-> new CourseNotFoundException("Course with id " + id + " not found"));
    }

    public Course createCourse(CourseCreateDto courseCreateDto,
                               String token){

        String keycloakId = jwtUtils.extractKeycloakId(token);
        Course course = courseCreateDto.getCourse();
        course.setCreatorId(keycloakId);
        return courseRepository.save(course);
    }

    public Course updateCourse(CourseUpdateDto courseUpdateDto,
                               String token){
        String keycloakId = jwtUtils.extractKeycloakId(token);
        Course courseOld = courseRepository.findById(courseUpdateDto.getId())
                        .orElseThrow(() -> new CourseNotFoundException("Course with id " + courseUpdateDto.getId() + " not found"));
        if(courseOld.getCreatorId().equals(keycloakId)){
            throw new WrongUserException("You are not allowed to update this course");
        }

        Course courseNew = courseUpdateDto.updateCourseDto(courseOld);

        return courseRepository.save(courseNew);
    }
}
