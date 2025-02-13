package com.example.course_service.dto.update;


import com.example.course_service.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseUpdateDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String creatorId;
    private Integer timesBought;
    private Double rating;
    private Set<String> categories;

    public Course updateCourseDto(Course course) {

        Course updatedCourse = new Course();

        updatedCourse.setName(this.name != null ? this.name : course.getName());
        updatedCourse.setDescription(this.description != null ? this.description : course.getDescription());
        updatedCourse.setPrice(this.price != null ? this.price : course.getPrice());
        updatedCourse.setCreatorId(this.creatorId != null ? this.creatorId : course.getCreatorId());
        updatedCourse.setTimesBought(this.timesBought != null ? this.timesBought : course.getTimesBought());
        updatedCourse.setRating(this.rating != null ? this.rating : course.getRating());
        updatedCourse.setCategories(this.categories != null ? this.categories : course.getCategories());

        return updatedCourse;

    }

}
