package com.example.course_service.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AssessmentTaskResponseDto implements TaskResponseDto{
    private Long taskId;
    private String title;
    private String description;
    private Boolean isTest;
    private Long moduleId;
    private String source;
    private List<QuestionResponseDto> questions;

}
