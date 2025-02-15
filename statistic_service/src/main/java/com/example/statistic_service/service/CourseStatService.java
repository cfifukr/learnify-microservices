package com.example.statistic_service.service;

import com.example.statistic_service.exception.StatNotFoundException;
import com.example.statistic_service.model.CourseStat;
import com.example.statistic_service.model.YearStat;
import com.example.statistic_service.repositories.CourseStatRepository;
import com.example.statistic_service.repositories.YearStatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseStatService {
    private final CourseStatRepository courseStatRepository;
    private final Logger logger = LoggerFactory.getLogger(CourseStatService.class);

    public CourseStatService(CourseStatRepository courseStatRepository) {
        this.courseStatRepository = courseStatRepository;
    }

    public CourseStat getCourseStatByCourseId(Long courseId) {
        return courseStatRepository.findById(courseId)
                .orElseThrow(() -> new StatNotFoundException("Course not found with id: " + courseId));

    }

    public CourseStat getCourseStatByCourseIdOrCreate(Long courseId){
        Optional<CourseStat> courseStatOpt = courseStatRepository.findById(courseId);

        if(courseStatOpt.isPresent()){
            return courseStatOpt.get();
        }

        //TODO: Request to Course Service to get info about course
        CourseStat courseStat = new CourseStat();
        courseStat.setCourseId(courseId);

        return null;
    }




}
