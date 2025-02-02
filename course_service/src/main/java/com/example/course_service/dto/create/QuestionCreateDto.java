package com.example.course_service.dto.create;

import com.example.course_service.model.Question;
import com.example.course_service.model.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionCreateDto {

    private String question;
    private String type;
    private List<String> options;
    private List<String> answers;
    private Long taskId;


    public Question toQuestion() {
        Question question = new Question();
        question.setQuestion(this.question);
        question.setQuestionType(QuestionType.valueOf(this.type.toUpperCase()));
        question.setOptions(this.options);
        question.setAnswers(this.answers);

        return question;
    }
}
