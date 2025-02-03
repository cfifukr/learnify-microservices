package com.example.course_service.dto.response;

import com.example.course_service.model.AssessmentTask;
import com.example.course_service.model.Question;
import com.example.course_service.model.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionResponseDto{
    private Long questionId;
    private String question;
    private List<String> options;
    private List<String> answers;
    private String questionType;


    public static QuestionResponseDto toDto(Question question){
        return QuestionResponseDto.builder()
                .questionId(question.getId())
                .question(question.getQuestion())
                .options(question.getOptions())
                .answers(question.getAnswers())
                .questionType(question.getQuestionType().toString())
                .build();
    }
}
