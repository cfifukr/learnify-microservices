package com.example.course_service.controller;

import com.example.course_service.dto.create.TaskCreateDto;
import com.example.course_service.model.Task;
import com.example.course_service.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;

    }


    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable Long taskId){
        Task task = taskService.getTaskById(taskId);

        return ResponseEntity.ok(task.toDto());
    }

    @PostMapping
    public ResponseEntity<?> createTask(@PathVariable TaskCreateDto dto){
        Task task = taskService.createTask(dto);

        return ResponseEntity.ok(task.toDto());
    }
}
