package com.example.course_service.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany
    private Set<Task> tasks = new HashSet<>();

    public CourseModule(String name, Integer durationMinutes, Course course, Integer place) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.course = course;
        this.place = place;
    }
    public CourseModule() {
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTast(Task task) {
        tasks.add(task);
    }

    public void removeTast(Task task) {
        tasks.remove(task);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CourseModule that = (CourseModule) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(durationMinutes, that.durationMinutes) && Objects.equals(place, that.place) && Objects.equals(course, that.course) && Objects.equals(that, that.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, durationMinutes, place, course, tasks);
    }


}
