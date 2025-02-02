package com.example.course_service.model;

import com.example.course_service.dto.create.TaskCreateDto;
import com.example.course_service.dto.response.TaskResponseDto;
import com.example.course_service.dto.response.VideoTaskResponseDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import javax.swing.text.View;

@Entity
@DiscriminatorValue("video")
public class VideoTask extends Task{

    private String source;

    public VideoTask(Long id, String title, String description, Boolean isTest, CourseModule courseModule, String source) {
        super(id, title, description, isTest, courseModule);
        this.source = source;
    }

    public VideoTask() {}

    @Override
    public TaskResponseDto toDto() {
        return VideoTaskResponseDto.builder()
                .taskId(this.getId())
                .isTest(false)
                .title(this.getTitle())
                .description(this.getDescription())
                .source(this.getSource())
                .moduleId(this.getCourseModule().getId())
                .build();

    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


}
