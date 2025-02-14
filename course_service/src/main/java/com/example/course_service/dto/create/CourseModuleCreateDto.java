package com.example.course_service.dto.create;


import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseModuleCreateDto {

    @NotNull(message = "Module name can`t be null")
    @Size(min = 1, max = 50, message = "Module name must be between 1 and 40")
    private String name;

    @NotNull(message = "Duration of module can`t be null")
    private Integer durationMinutes;

    @NotNull(message = "Place of module in course can`t be null")
    private Integer place;

    public CourseModule getCourseModule(Course course){
        CourseModule courseModule = new CourseModule();
        courseModule.setName(this.getName());
        courseModule.setPlace(this.getPlace());
        courseModule.setDurationMinutes(this.getDurationMinutes());
        courseModule.setCourse(course);
        return courseModule;
    }
}
