package com.example.statistic_service.service;

import com.example.statistic_service.dto.microservice.CourseResponse;
import com.example.statistic_service.exception.CourseNotFoundException;
import com.example.statistic_service.exception.StatNotFoundException;
import com.example.statistic_service.model.CourseStat;
import com.example.statistic_service.repositories.CourseStatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class CourseStatService {
    private final CourseStatRepository courseStatRepository;
    private final Logger logger = LoggerFactory.getLogger(CourseStatService.class);
    private final RestClient restClient;

    public CourseStatService(CourseStatRepository courseStatRepository,
                             RestClient restClient) {
        this.courseStatRepository = courseStatRepository;
        this.restClient = restClient;
    }

    @Transactional(readOnly = true)
    public CourseStat getCourseStatByCourseId(Long courseId) {
        return courseStatRepository.findById(courseId)
                .orElseThrow(() -> new StatNotFoundException("Course not found with id: " + courseId));

    }

    @Transactional(readOnly = true)
    public CourseStat getCourseStatByCourseIdOrCreate(Long courseId){
        Optional<CourseStat> courseStatOpt = courseStatRepository.findById(courseId);

        if(courseStatOpt.isPresent()){
            return courseStatOpt.get();
        }

        CourseStat courseStat = getCourseData(courseId).getCourseStat();

        return courseStatRepository.save(courseStat);
    }

    private CourseResponse getCourseData(Long courseId){
        CourseResponse response =  restClient.get()
                .uri("http://COURSE-SERVICE/{courseId}", courseId)
                .retrieve()
                .body(CourseResponse.class);
        if(response == null){
            throw new CourseNotFoundException("Course not found with id: " + courseId);
        }
        return response;
    }




}
