package com.example.course_service.model;


import jakarta.persistence.*;

import java.util.*;

@Entity
public class CourseProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String keycloakId;

    @Column(nullable = false)
    private Long courseId;

    @Column(nullable = false)
    private Boolean status;

    @OneToMany
    private Set<ModuleProgress> modulesProgress = new HashSet<>();


    public CourseProgress(String keycloakId, Long courseId, Boolean status, Set<ModuleProgress> modulesProgress, Long id) {
        this.keycloakId = keycloakId;
        this.courseId = courseId;
        this.status = status;
        this.modulesProgress = modulesProgress;
        this.id = id;
    }

    public CourseProgress(){}




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<ModuleProgress> getModulesProgress() {
        return modulesProgress;
    }

    public void setModulesProgress(Set<ModuleProgress> modulesProgress) {
        this.modulesProgress = modulesProgress;
    }

    public void addModule(ModuleProgress module) {
        modulesProgress.add(module);
    }

    public void removeModule(ModuleProgress module) {
        modulesProgress.remove(module);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CourseProgress that = (CourseProgress) o;
        return Objects.equals(id, that.id) && Objects.equals(keycloakId, that.keycloakId) && Objects.equals(courseId, that.courseId) && Objects.equals(status, that.status) && Objects.equals(modulesProgress, that.modulesProgress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keycloakId, courseId, status);
    }


}
