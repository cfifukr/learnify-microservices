package com.example.course_service.model;

import com.example.course_service.dto.response.AssessmentTaskResponseDto;
import com.example.course_service.dto.response.QuestionResponseDto;
import com.example.course_service.dto.response.ReadTaskResponseDto;
import com.example.course_service.dto.response.TaskResponseDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@DiscriminatorValue("assessment")
public class AssessmentTask extends Task {


    @OneToMany(mappedBy = "assessmentTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions;

    public AssessmentTask() {}


    public AssessmentTask(Long id, String title, String description, Boolean isTest, CourseModule courseModule, String title1, Set<Question> questions) {
        super(id, title, description, isTest, courseModule);
        this.questions = questions;
    }

    @Override
    public TaskResponseDto toDto() {
        AssessmentTaskResponseDto dto =  AssessmentTaskResponseDto.builder()
                .taskId(this.getId())
                .isTest(false)
                .title(this.getTitle())
                .description(this.getDescription())
                .moduleId(this.getCourseModule().getId())
                .build();

        List<QuestionResponseDto> questionDtos = new ArrayList<>();
        this.questions.forEach(question -> {
            questionDtos.add(QuestionResponseDto.toDto(question));
        });

        dto.setQuestions(questionDtos);

        return dto;


    }


    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AssessmentTask that = (AssessmentTask) o;
        return  Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questions);
    }


    @Override
    public String toString() {
        return "AssessmentTask{" +
                ", questions=" + questions +
                '}';
    }
}
