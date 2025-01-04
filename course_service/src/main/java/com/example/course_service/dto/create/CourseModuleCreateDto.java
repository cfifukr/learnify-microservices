package com.example.course_service.dto.create;


import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;

public class CourseModuleCreateDto {
    private String name;
    private Integer durationMinutes;
    private Integer place;

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

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public CourseModule getCourseModule(Course course){
        CourseModule courseModule = new CourseModule();
        courseModule.setName(this.getName());
        courseModule.setPlace(this.getPlace());
        courseModule.setDurationMinutes(this.getDurationMinutes());
        courseModule.setCourse(course);
        return courseModule;
    }
}
