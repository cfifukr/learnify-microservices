package com.example.course_service.model;

import jakarta.persistence.*;

@Entity
public class CourseModule {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer durationMinutes;

    private Integer place;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public CourseModule(String name, Integer durationMinutes, Course course, Integer place) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.course = course;
        this.place = place;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public CourseModule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
