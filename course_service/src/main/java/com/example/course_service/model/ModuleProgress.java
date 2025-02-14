package com.example.course_service.model;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class ModuleProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String keycloakId;

    @ManyToOne
    private CourseProgress courseProgress;

    @Column(nullable = false)
    private Long moduleId;

    @Column(nullable = false)
    private Boolean moduleStatus;

    @ElementCollection
    @CollectionTable(name = "progress_map", joinColumns = @JoinColumn(name = "task_id"))
    @MapKeyColumn(name = "task_id_column")
    @Column(name = "status_column")
    private Map<Long, Boolean> tasksStatus = new HashMap<>();


    public ModuleProgress() {}

    public ModuleProgress(Long id, String keycloakId, CourseProgress courseProgress, Long moduleId, Boolean moduleStatus, Map<Long, Boolean> tasksStatus) {
        this.id = id;
        this.keycloakId = keycloakId;
        this.courseProgress = courseProgress;
        this.moduleId = moduleId;
        this.moduleStatus = moduleStatus;
        this.tasksStatus = tasksStatus;
    }

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

    public CourseProgress getCourseProgress() {
        return courseProgress;
    }

    public void setCourseProgress(CourseProgress course) {
        this.courseProgress = courseProgress;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Boolean getModuleStatus() {
        return moduleStatus;
    }

    public void setModuleStatus(Boolean moduleStatus) {
        this.moduleStatus = moduleStatus;
    }

    public Map<Long, Boolean> getTasksStatus() {
        return tasksStatus;
    }

    public void setTasksStatus(Map<Long, Boolean> tasksStatus) {
        this.tasksStatus = tasksStatus;
    }

    public void addOrChangeTaskStatus(Long taskId, Boolean status) {
        tasksStatus.put(taskId, status);
    }
    public void removeTaskStatus(Long taskId) {
        tasksStatus.remove(taskId);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ModuleProgress that = (ModuleProgress) o;
        return Objects.equals(id, that.id) && Objects.equals(keycloakId, that.keycloakId) && Objects.equals(courseProgress, that.courseProgress) && Objects.equals(moduleId, that.moduleId) && Objects.equals(moduleStatus, that.moduleStatus) && Objects.equals(tasksStatus, that.tasksStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keycloakId, moduleId, moduleStatus, tasksStatus);
    }
}
