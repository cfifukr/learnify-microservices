package com.example.statistic_service.service;

import com.example.statistic_service.exception.StatNotFoundException;
import com.example.statistic_service.model.CourseStat;
import com.example.statistic_service.model.YearStat;
import com.example.statistic_service.repositories.CourseStatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

}
