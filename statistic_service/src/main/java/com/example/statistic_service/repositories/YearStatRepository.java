package com.example.statistic_service.repositories;

import com.example.statistic_service.model.YearStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface YearStatRepository extends JpaRepository<YearStat, Integer> {

    @Query("SELECT y FROM YearStat y WHERE y.courseId = :courseId AND y.year = :year")
    Optional<YearStat> findByCourseIdAndYear(@Param("courseId") Long courseId, @Param("year") int year);


    @Query("SELECT y FROM YearStat y WHERE y.teacherId = :teacherId AND y.year = :year")
    Optional<YearStat> findByTeacherIdAndYear(@Param("teacherId") Long teacherId, @Param("year") int year);
}
