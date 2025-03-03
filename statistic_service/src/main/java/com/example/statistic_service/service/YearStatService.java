package com.example.statistic_service.service;


import com.example.statistic_service.exception.StatNotFoundException;
import com.example.statistic_service.model.YearStat;
import com.example.statistic_service.repositories.YearStatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;
import java.time.Year;
import java.util.HashMap;

@Service
public class YearStatService {
    private final YearStatRepository yearStatRepository;
    private final Logger logger = LoggerFactory.getLogger(YearStatService.class);

    public YearStatService(YearStatRepository yearStatRepository) {
        this.yearStatRepository = yearStatRepository;
    }

    @Transactional(readOnly = true)
    public YearStat getYearStatByYearAndTeacherId(Long teacherId, int year) {
        YearStat yearStat = yearStatRepository.findByTeacherIdAndYear(teacherId, year)
                .orElseThrow(() -> new StatNotFoundException("YearStat was not found with teacherId " + teacherId));

        return yearStat;
    }

    @Transactional(readOnly = true)
    public YearStat getYearStatByYearAndCourseId(Long courseId, int year) {
        YearStat yearStat = yearStatRepository.findByCourseIdAndYear(courseId, year)
                .orElseThrow(() -> new StatNotFoundException("YearStat was not found with courseId " + courseId));

        return yearStat;
    }

    @Transactional
    public void incrementCourseStat(Long courseId, Month month) {
        YearStat yearStat = yearStatRepository.findByCourseIdAndYear(courseId, Year.now().getValue())
                .orElse(new YearStat(null, Year.now().getValue(), courseId, null, new HashMap<>()));


        incrementMonthlyCount(yearStat, month);
    }

    @Transactional
    public void incrementTeacherStat(Long teacherId, Month month) {
        YearStat yearStat = yearStatRepository.findByTeacherIdAndYear(teacherId, Year.now().getValue())
                .orElseThrow(() -> new StatNotFoundException("YearStat not found for teacherId: " + teacherId));

        incrementMonthlyCount(yearStat, month);
    }

    private void incrementMonthlyCount(YearStat yearStat, Month month) {
        yearStat.getMonthlyCounts().merge(month, 1L, Long::sum);

        yearStatRepository.save(yearStat);
        logger.info("Incremented count for {} in stat with id:{} for year {}", month, yearStat.getId(), Year.now().getValue());
    }
}
