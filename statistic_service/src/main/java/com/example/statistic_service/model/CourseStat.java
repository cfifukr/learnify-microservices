package com.example.statistic_service.model;


import jakarta.persistence.*;



import java.util.List;

@Entity
public class CourseStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> keywords;

    private Long courseId;

    private String creatorKeycloakId;

    private String courseName;

    @OneToMany
    private List<YearStat> yearStatsList;

    public CourseStat() {}

    public CourseStat(Long id, Long courseId, List<String> keywords, String creatorKeycloakId, String courseName, List<YearStat> yearStatsList) {
        this.id = id;
        this.courseId = courseId;
        this.keywords = keywords;
        this.creatorKeycloakId = creatorKeycloakId;
        this.courseName = courseName;
        this.yearStatsList = yearStatsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getCreatorKeycloakId() {
        return creatorKeycloakId;
    }

    public void setCreatorKeycloakId(String creatorKeycloakId) {
        this.creatorKeycloakId = creatorKeycloakId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<YearStat> getYearStatsList() {
        return yearStatsList;
    }

    public void setYearStatsList(List<YearStat> yearStatsList) {
        this.yearStatsList = yearStatsList;
    }

    @Override
    public String toString() {
        return "CourseStat{" +
                "id=" + id +
                ", keywords=" + keywords +
                ", courseId=" + courseId +
                ", creatorKeycloakId='" + creatorKeycloakId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", yearStatsList=" + yearStatsList +
                '}';
    }
}
