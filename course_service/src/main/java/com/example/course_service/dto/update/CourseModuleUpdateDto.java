package com.example.course_service.dto.update;

import com.example.course_service.model.CourseModule;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseModuleUpdateDto{

    @NotNull(message = "ModuleId can`t be null")
    private Long id;

    @NotNull(message = "Module Name can`t be null")
    @Size(min = 1, max = 50, message = "Module name must be between 1 and 40")
    private String name;

    @NotNull(message = "Module duration in course can`t be null")
    private Integer durationMinutes;

    @NotNull(message = "Module place in course can`t be null")
    private Integer place;

    @NotNull(message = "Course id can`t be null")
    private Long courseId;



    public CourseModule updateCourseDto(CourseModule module) {

        CourseModule moduleUpdated = new CourseModule();

        moduleUpdated.setName(this.name != null ? this.name : module.getName());
        moduleUpdated.setDurationMinutes(this.durationMinutes != null ? this.durationMinutes : module.getDurationMinutes());
        moduleUpdated.setPlace(this.place != null ? this.place : module.getPlace());


        return moduleUpdated;

    }


}
