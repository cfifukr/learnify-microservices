package com.example.course_service.dto.response;

import com.example.course_service.model.CourseProgress;
import com.example.course_service.model.ModuleProgress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleProgressResponseDto {

    private Long id;

    private String keycloakId;
    private Long courseProgressId;

    private Long moduleId;
    private Boolean moduleStatus;

    private Map<Long, Boolean> tasksStatus = new HashMap<>();

    public static ModuleProgressResponseDto getDto(ModuleProgress moduleProgress) {
        return ModuleProgressResponseDto.builder()
                .id(moduleProgress.getId())
                .moduleId(moduleProgress.getModuleId())
                .moduleStatus(moduleProgress.getModuleStatus())
                .keycloakId(moduleProgress.getKeycloakId())
                .tasksStatus(moduleProgress.getTasksStatus())
                .build();
    }

}
