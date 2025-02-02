package com.example.course_service.dto.create;

import com.example.course_service.model.CourseModule;
import com.example.course_service.model.ReadTask;
import com.example.course_service.model.Task;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReadTaskCreateDto implements TaskCreateDto{
    private String title;
    private String description;
    private String text;
    private Long moduleId;

    @Override
    public Long getModuleId() {
        return this.moduleId;
    }

    @Override
    public Task toTask(CourseModule courseModule) {
        ReadTask readTask = new ReadTask();
        readTask.setTitle(title);
        readTask.setDescription(description);
        readTask.setTest(false);
        readTask.setText(text);
        readTask.setCourseModule(courseModule);

        return readTask;
    }
}
