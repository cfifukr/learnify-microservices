package com.example.statistic_service.model;

import jakarta.persistence.*;

import java.time.Month;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class YearStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;

    @Column(unique = true)
    private Long courseId;

    @Column(unique = true)
    private Long teacherId;

    @ElementCollection
    @CollectionTable(name = "year_stat_monthly_counts", joinColumns = @JoinColumn(name = "year_stat_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "count")
    private Map<Month, Long> monthlyCounts = new EnumMap<>(Month.class);

    public YearStat() {
    }

    public YearStat(Long id, int year,Long courseId, Long teacherId, Map<Month, Long> monthlyCounts) {
        this.id = id;
        this.year = year;
        this.monthlyCounts = monthlyCounts;
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public Map<Month, Long> getMonthlyCounts() {
        return monthlyCounts;
    }

    public void setMonthlyCounts(Map<Month, Long> monthlyCounts) {
        this.monthlyCounts = monthlyCounts;
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
        YearStat yearStat = (YearStat) o;
        return year == yearStat.year && Objects.equals(id, yearStat.id) && Objects.equals(teacherId, yearStat.teacherId) && Objects.equals(courseId, yearStat.courseId) && Objects.equals(monthlyCounts, yearStat.monthlyCounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, courseId, teacherId, monthlyCounts);
    }
}
