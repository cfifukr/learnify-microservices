package com.example.course_service.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReadTaskResponseDto implements TaskResponseDto{
    private Long taskId;
    private String title;
    private String description;
    private Boolean isTest;
    private Long moduleId;
    private String text;

}
