package com.example.course_service.dto.create;

import com.example.course_service.model.Course;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseCreateDto {

    @NotNull
    @Size(min = 3, max = 50, message = "Course name length must be between 3 and 50 characters")
    private String name;

    @Max(value = 500, message = "Course description can`t be longer than 500 characters")
    private String description;

    private Double price;

    private List<String> categories = new ArrayList<>();


    public Course getCourse(){

        Course course = new Course();
        course.setName(this.getName());
        course.setDescription(this.getDescription());
        course.setPrice(this.price != null && this.price > 0.00 ? this.price : 0.00);
        course.setCategories(this.categories != null ?
                new HashSet<>(this.getCategories())
                : new HashSet<>());
        course.setTimesBought(0);


        return course;

    }



}
