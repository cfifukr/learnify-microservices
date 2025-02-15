package com.example.core;

import java.util.Objects;

public class UserEnrolledStatisticEvent {
    private String creatorKeycloakId;
    private String studentKeycloakId;
    private Long courseId;

    public UserEnrolledStatisticEvent() {}

    public UserEnrolledStatisticEvent(String creatorKeycloakId, String studentKeycloakId, Long courseId) {
        this.creatorKeycloakId = creatorKeycloakId;
        this.studentKeycloakId = studentKeycloakId;
        this.courseId = courseId;
    }

    public String getCreatorKeycloakId() {
        return creatorKeycloakId;
    }

    public void setCreatorKeycloakId(String creatorKeycloakId) {
        this.creatorKeycloakId = creatorKeycloakId;
    }

    public String getStudentKeycloakId() {
        return studentKeycloakId;
    }

    public void setStudentKeycloakId(String studentKeycloakId) {
        this.studentKeycloakId = studentKeycloakId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEnrolledStatisticEvent that = (UserEnrolledStatisticEvent) o;
        return Objects.equals(creatorKeycloakId, that.creatorKeycloakId) && Objects.equals(studentKeycloakId, that.studentKeycloakId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creatorKeycloakId, studentKeycloakId, courseId);
    }

    @Override
    public String toString() {
        return "UserEnrolledStatisticEvent{" +
                "creatorKeycloakId='" + creatorKeycloakId + '\'' +
                ", studentKeycloakId='" + studentKeycloakId + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
