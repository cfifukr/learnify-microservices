package com.example.course_service.controller;

import com.example.course_service.dto.create.AssessmentTaskCreateDto;
import com.example.course_service.dto.create.ReadTaskCreateDto;
import com.example.course_service.dto.create.TaskCreateDto;
import com.example.course_service.dto.create.VideoTaskCreateDto;
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

    @PostMapping("/video")
    public ResponseEntity<?> createTaskVideo(@RequestBody VideoTaskCreateDto dto){
        Task task = taskService.createTask(dto);

        return ResponseEntity.ok(task.toDto());
    }

    @PostMapping("/read")
    public ResponseEntity<?> createTaskRead(@RequestBody ReadTaskCreateDto dto){
        Task task = taskService.createTask(dto);

        return ResponseEntity.ok(task.toDto());
    }

    @PostMapping("/assessment")
    public ResponseEntity<?> createTaskAssessment(@RequestBody AssessmentTaskCreateDto dto){
        Task task = taskService.createTask(dto);

        return ResponseEntity.ok(task.toDto());
    }
}
