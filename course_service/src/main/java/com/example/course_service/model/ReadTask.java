package com.example.course_service.model;

import com.example.course_service.dto.response.ReadTaskResponseDto;
import com.example.course_service.dto.response.TaskResponseDto;
import com.example.course_service.dto.response.VideoTaskResponseDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("read")
public class ReadTask extends Task{
    private String text;

    public ReadTask(Long id, String title, String description, Boolean isTest, CourseModule courseModule, String text) {
        super(id, title, description, isTest, courseModule);
        this.text = text;
    }

    @Override
    public TaskResponseDto toDto() {
        return  ReadTaskResponseDto.builder()
                .taskId(this.getId())
                .isTest(false)
                .title(this.getTitle())
                .description(this.getDescription())
                .text(this.getText())
                .moduleId(this.getCourseModule().getId())
                .build();

    }

    public ReadTask() {}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
