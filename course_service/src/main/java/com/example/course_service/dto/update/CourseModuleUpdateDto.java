package com.example.course_service.dto.update;

import com.example.course_service.model.CourseModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseModuleUpdateDto{

    private Long id;
    private String name;
    private Integer durationMinutes;
    private Integer place;

    private Long courseId;



    public CourseModule updateCourseDto(CourseModule module) {

        CourseModule moduleUpdated = new CourseModule();

        moduleUpdated.setName(this.name != null ? this.name : module.getName());
        moduleUpdated.setDurationMinutes(this.durationMinutes != null ? this.durationMinutes : module.getDurationMinutes());
        moduleUpdated.setPlace(this.place != null ? this.place : module.getPlace());


        return moduleUpdated;

    }


}
