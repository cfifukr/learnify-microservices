package com.example.course_service.service;

import com.example.course_service.dto.PageDto;
import com.example.course_service.dto.create.CourseCreateDto;
import com.example.course_service.dto.response.CourseResponseDto;
import com.example.course_service.exceptions.CourseNotFoundException;
import com.example.course_service.model.Course;
import com.example.course_service.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

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

    public Course saveCourse(CourseCreateDto courseCreateDto){

        Course course = courseCreateDto.getCourse();
        return courseRepository.save(course);
    }
}
