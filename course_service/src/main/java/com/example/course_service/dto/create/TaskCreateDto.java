package com.example.course_service.dto.create;

import com.example.course_service.model.CourseModule;
import com.example.course_service.model.Question;
import com.example.course_service.model.Task;

import java.util.List;

public interface TaskCreateDto<T extends Task> {
     T toTask(CourseModule courseModule);
     Long getModuleId();
}
