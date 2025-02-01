package com.example.course_service.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


@Entity
public class AssessmentTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "assessmentTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions;

    public AssessmentTask() {}

    public AssessmentTask(String title) {
        this.title = title;
    }
    public AssessmentTask(String title, Set<Question> questions) {
        this.title = title;
        this.questions = questions;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AssessmentTask that = (AssessmentTask) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, questions);
    }

    @Override
    public String toString() {
        return "AssesmestTask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                '}';
    }
}
