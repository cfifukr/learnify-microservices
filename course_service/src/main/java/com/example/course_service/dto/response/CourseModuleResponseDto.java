package com.example.course_service.dto.response;


import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseModuleResponseDto {
    private Long id;
    private String name;
    private Integer durationMinutes;
    private Long courseId;
    private Integer place;


    public static CourseModuleResponseDto getDto(CourseModule courseModule){
        CourseModuleResponseDto dto = new CourseModuleResponseDto();
        dto.setId(courseModule.getId());
        dto.setCourseId(courseModule.getCourse().getId());
        dto.setName(courseModule.getName());
        dto.setDurationMinutes(courseModule.getDurationMinutes());
        dto.setPlace(courseModule.getPlace());

        return dto;

    }

}
