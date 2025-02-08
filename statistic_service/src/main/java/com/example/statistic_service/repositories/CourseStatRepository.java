package com.example.statistic_service.repositories;

import com.example.statistic_service.model.CourseStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseStatRepository extends JpaRepository<CourseStat, Long> {

}
