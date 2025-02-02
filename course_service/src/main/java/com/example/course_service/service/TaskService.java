package com.example.course_service.service;


import com.example.course_service.dto.create.AssessmentTaskCreateDto;
import com.example.course_service.dto.create.TaskCreateDto;
import com.example.course_service.exceptions.ModuleNotFoundException;
import com.example.course_service.exceptions.TaskNotFoundException;
import com.example.course_service.model.AssessmentTask;
import com.example.course_service.model.CourseModule;
import com.example.course_service.model.Task;
import com.example.course_service.repository.CourseModuleRepository;
import com.example.course_service.repository.QuestionRepository;
import com.example.course_service.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final CourseModuleRepository courseModuleRepository;
    private final QuestionRepository questionRepository;

    public TaskService(TaskRepository taskRepository,
                       CourseModuleRepository courseModuleRepository,
                       QuestionRepository questionRepository) {
      this.taskRepository = taskRepository;
      this.courseModuleRepository = courseModuleRepository;
      this.questionRepository = questionRepository;
    }

    public Page<Task> getAllPageable(Pageable pageable){
        return taskRepository.findAll(pageable);
    }


    public Task createTask(TaskCreateDto createDto) {

        CourseModule module = courseModuleRepository.findById(createDto.getModuleId())
                .orElseThrow(()-> new ModuleNotFoundException("Module not found with id: " + createDto.getModuleId()));

        Task task = createDto.toTask(module);

        taskRepository.save(task);
        return task;
    }


    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("Task not found with id: " + id));
    }
}
