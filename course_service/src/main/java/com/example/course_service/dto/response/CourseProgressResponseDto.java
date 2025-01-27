package com.example.course_service.dto.response;


import com.example.course_service.model.CourseProgress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseProgressResponseDto {

    private Long id;

    private String keycloakId;

    private Long courseId;
    private Boolean status;

    private Set<ModuleProgressResponseDto> modulesProgress = new HashSet<>();


    public static CourseProgressResponseDto getDtoWithModules(CourseProgress courseProgress) {
        CourseProgressResponseDto dto = CourseProgressResponseDto.builder()
                .id(courseProgress.getId())
                .courseId(courseProgress.getCourseId())
                .keycloakId(courseProgress.getKeycloakId())
                .status(courseProgress.getStatus())
                .modulesProgress(new HashSet<>())
                .build();

        courseProgress.getModulesProgress()
                .forEach(module -> dto.getModulesProgress().add(ModuleProgressResponseDto.getDto(module)));

        return dto;
    }



}
