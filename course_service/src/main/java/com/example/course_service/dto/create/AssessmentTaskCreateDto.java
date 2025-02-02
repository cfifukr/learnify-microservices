package com.example.course_service.dto.create;

import com.example.course_service.model.AssessmentTask;
import com.example.course_service.model.CourseModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AssessmentTaskCreateDto implements TaskCreateDto {
    private String title;
    private String description;
    private List<QuestionCreateDto> questions;
    private Long moduleId;

    @Override
    public Long getModuleId() {
        return this.moduleId;
    }

    @Override
    public AssessmentTask toTask(CourseModule courseModule) {
        AssessmentTask assessmentTask = new AssessmentTask();
        assessmentTask.setTitle(title);
        assessmentTask.setDescription(description);
        assessmentTask.setTest(true);

        if (questions != null) {
            questions.forEach(questionDto -> {
                assessmentTask.addQuestion(questionDto.toQuestion());
            });
        }

        return assessmentTask;
    }

}
