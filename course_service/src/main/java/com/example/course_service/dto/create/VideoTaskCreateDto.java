package com.example.course_service.dto.create;

import com.example.course_service.model.CourseModule;
import com.example.course_service.model.Task;
import com.example.course_service.model.VideoTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class VideoTaskCreateDto implements TaskCreateDto{

    private String title;
    private String description;
    private Long moduleId;
    private String source;

    @Override
    public Long getModuleId() {
        return this.moduleId;
    }

    @Override
    public Task toTask(CourseModule courseModule) {
        VideoTask videoTask = new VideoTask();
        videoTask.setTitle(title);
        videoTask.setDescription(description);
        videoTask.setCourseModule(courseModule);
        videoTask.setTest(false);
        videoTask.setSource(source);

        return videoTask;
    }
}
