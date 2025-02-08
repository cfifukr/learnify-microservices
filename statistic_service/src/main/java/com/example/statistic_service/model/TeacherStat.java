package com.example.statistic_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TeacherStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keycloakId;

    @OneToMany
    private List<YearStat> yearStatList;


    public TeacherStat() {
    }

    public TeacherStat(String keycloakId, Long id, List<YearStat> yearStatList) {
        this.keycloakId = keycloakId;
        this.id = id;
        this.yearStatList = yearStatList;
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

    public List<YearStat> getYearStatList() {
        return yearStatList;
    }

    public void setYearStatList(List<YearStat> yearStatList) {
        this.yearStatList = yearStatList;
    }


    @Override
    public String toString() {
        return "TeacherStat{" +
                "id=" + id +
                ", keycloakId='" + keycloakId + '\'' +
                ", yearStatList=" + yearStatList +
                '}';
    }
}
