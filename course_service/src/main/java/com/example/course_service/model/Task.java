package com.example.course_service.model;

import com.example.course_service.dto.create.TaskCreateDto;
import com.example.course_service.dto.response.TaskResponseDto;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Boolean isTest;

    @ManyToOne
    private CourseModule courseModule;

    public Task(Long id, String title, String description, Boolean isTest, CourseModule courseModule) {
        this.courseModule = courseModule;
        this.id = id;
        this.title = title;
        this.description = description;
        this.isTest = isTest;
    }

    public Task() {}


    public abstract TaskResponseDto toDto();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getTest() {
        return isTest;
    }

    public void setTest(Boolean test) {
        isTest = test;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourseModule getCourseModule() {
        return courseModule;
    }

    public void setCourseModule(CourseModule courseModule) {
        this.courseModule = courseModule;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(isTest, task.isTest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, isTest);
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isTest=" + isTest +
                ", courseModule=" + courseModule +
                '}';
    }
}
