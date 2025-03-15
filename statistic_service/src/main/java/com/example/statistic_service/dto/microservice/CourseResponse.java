package com.example.statistic_service.dto.microservice;

import com.example.statistic_service.model.CourseStat;
import com.example.statistic_service.model.YearStat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseResponse {
    private Long courseId;
    private String courseName;
    private String creatorKeycloakId;
    private Set<String> keywords;

    public CourseStat getCourseStat() {
        CourseStat courseStat = new CourseStat();
        courseStat.setCourseId(courseId);
        courseStat.setCourseName(courseName);
        courseStat.setCreatorKeycloakId(creatorKeycloakId);
        courseStat.setKeywords(keywords.stream().toList());
        return courseStat;
    }
}
