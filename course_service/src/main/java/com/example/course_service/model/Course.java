package com.example.course_service.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = true, length = 500)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String creatorKeycloakId;

    @Column(nullable = false)
    private Integer timesBought;

    private Double rating;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> categories = new HashSet<>();

    @OneToMany(mappedBy="course", fetch = FetchType.EAGER)
    private Set<CourseModule> modules;

    public Course() {
    }

    public Course(String name, Long id, String description, Double price, String creatorKeycloakId, Integer timesBought, Double rating, Set<String> categories, Set<CourseModule> modules) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.price = price;
        this.creatorKeycloakId = creatorKeycloakId;
        this.timesBought = timesBought;
        this.rating = rating;
        this.categories = categories;
        this.modules = modules;
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
        return creatorKeycloakId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorKeycloakId = creatorId;
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

    public Set<CourseModule> getModules() {
        return modules;
    }

    public void setModules(Set<CourseModule> modules) {
        this.modules = modules;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(name, course.name) && Objects.equals(description, course.description) && Objects.equals(price, course.price) && Objects.equals(creatorKeycloakId, course.creatorKeycloakId) && Objects.equals(timesBought, course.timesBought) && Objects.equals(rating, course.rating) && Objects.equals(categories, course.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, creatorKeycloakId, timesBought, rating, categories);
    }

}
