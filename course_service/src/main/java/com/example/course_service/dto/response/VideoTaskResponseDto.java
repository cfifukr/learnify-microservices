package com.example.course_service.dto.response;

import com.example.course_service.model.CourseModule;
import jakarta.persistence.GeneratedValue;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VideoTaskResponseDto implements TaskResponseDto{
    private Long taskId;
    private String title;
    private String description;
    private Boolean isTest;
    private Long moduleId;
    private String source;

}
