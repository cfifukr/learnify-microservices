package com.example.course_service.repository;

import com.example.course_service.model.CourseModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseModuleRepository extends JpaRepository<CourseModule, Long> {

    @Query("SELECT cm FROM CourseModule cm WHERE cm.course.id = :courseId ORDER BY cm.place")
    Page<CourseModule> getCourseModulesByCourseId(@Param("courseId") Long courseId, Pageable pageable);

}
