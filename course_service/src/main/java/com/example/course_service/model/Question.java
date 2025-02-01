package com.example.course_service.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @ElementCollection
    @CollectionTable(name = "question_answers", joinColumns = @JoinColumn(name = "question_id"))
    private Set<String> answers;

    @ElementCollection
    @CollectionTable(name = "question_correct_answers", joinColumns = @JoinColumn(name = "question_id"))
    private List<String> correctAnswers;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_task_id", nullable = false)
    private AssessmentTask assessmentTask;

    public Question() {}

    public Question(String question, Set<String> answers,
                    List<String> correctAnswers, QuestionType questionType,
                    AssessmentTask assessmentTask) {
        this.question = question;
        this.answers = answers;
        this.correctAnswers = correctAnswers;
        this.questionType = questionType;
        this.assessmentTask = assessmentTask;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<String> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<String> answers) {
        this.answers = answers;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(id, question1.id) && Objects.equals(question, question1.question) && Objects.equals(answers, question1.answers) && Objects.equals(correctAnswers, question1.correctAnswers) && questionType == question1.questionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answers, correctAnswers, questionType);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                ", correctAnswers=" + correctAnswers +
                ", questionType=" + questionType +
                '}';
    }
}
