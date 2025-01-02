package com.example.course_service.model;

import jakarta.persistence.*;

@Entity
public class CourseModule {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer durationMinutes;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
