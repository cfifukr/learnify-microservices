package com.example.course_service.dto.response;


import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CourseModuleResponseDto {
    private Long id;
    private String name;
    private Integer durationMinutes;
    private Long courseId;
    private Integer place;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

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
