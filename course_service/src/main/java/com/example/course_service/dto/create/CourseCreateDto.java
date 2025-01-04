package com.example.course_service.dto.create;

import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CourseCreateDto {

    private String name;
    private String description;
    private Double price;

    private List<String> categories = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Course getCourse(){

        Course course = new Course();
        course.setName(this.getName());
        course.setDescription(this.getDescription());
        course.setPrice(this.getPrice());
        course.setCategories(this.getCategories()
                .stream()
                .collect(Collectors.toSet()));

        return course;

    }



}
