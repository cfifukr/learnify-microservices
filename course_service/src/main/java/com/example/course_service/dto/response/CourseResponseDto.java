package com.example.course_service.dto.response;

import com.example.course_service.model.Course;

import java.util.List;


public class CourseResponseDto {
    private Long id;
    private String name;
    private String description;
    private String price;
    private Long creatorId;
    private Integer timesBought;
    private Double rating;
    private List<String> categories;

    //private Set<CourseModule> modules;


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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
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

        return dto;
    }
}
