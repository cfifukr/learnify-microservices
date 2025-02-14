package com.example.course_service.dto.create;

import com.example.course_service.model.CourseModule;
import com.example.course_service.model.Task;


public interface TaskCreateDto<T extends Task> {
     T toTask(CourseModule courseModule);
     Long getModuleId();
}
