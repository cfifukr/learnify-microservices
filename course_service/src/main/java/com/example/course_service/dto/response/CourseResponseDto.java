package com.example.course_service.dto.response;

import com.example.course_service.model.Course;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class CourseResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String creatorId;
    private Integer timesBought;
    private Double rating;
    private Set<String> categories;

    private Set<CourseModuleResponseDto> modules;


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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getTimesBought() {
        return timesBought;
    }

    public void setTimesBought(Integer timesBought) {
        this.timesBought = timesBought;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Set<CourseModuleResponseDto> getModules() {
        return modules;
    }

    public void setModules(Set<CourseModuleResponseDto> modules) {
        this.modules = modules;
    }

    public static CourseResponseDto getDto(Course course){
        CourseResponseDto dto = new CourseResponseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setCategories(course.getCategories());
        dto.setDescription(course.getDescription());
        dto.setCreatorId(course.getCreatorId());
        dto.setRating(course.getRating());
        dto.setTimesBought(course.getTimesBought());
        dto.setPrice(course.getPrice());

        if(course.getModules()!=null) {
            dto.setModules(course.getModules()
                    .stream().map(i -> CourseModuleResponseDto.getDto(i))
                    .collect(Collectors.toSet()));

        }
        return dto;
    }
}
