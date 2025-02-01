package com.example.course_service.service;


import com.example.course_service.repository.QuestionRepository;
import com.example.course_service.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class AssesmentTaskService {
    private final QuestionRepository questionRepository;
    private final TaskRepository taskRepository;

    public AssesmentTaskService(QuestionRepository questionRepository, TaskRepository taskRepository) {
        this.questionRepository = questionRepository;
        this.taskRepository = taskRepository;
    }
}
