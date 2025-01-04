package com.example.course_service.service;


import com.example.course_service.model.Task;
import com.example.course_service.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
      this.taskRepository = taskRepository;
    }

    public Page<Task> getAllPageable(Pageable pageable){
        return taskRepository.findAll(pageable);
    }

}
