package com.example.course_service;

import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;
import com.example.course_service.model.Task;
import com.example.course_service.model.VideoTask;
import com.example.course_service.repository.CourseModuleRepository;
import com.example.course_service.repository.CourseRepository;
import com.example.course_service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableDiscoveryClient
public class CourseServiceApplication implements CommandLineRunner {

    @Autowired
    private  CourseRepository courseRepository;

    @Autowired
    private CourseModuleRepository courseModuleRepository;

    @Autowired
    private TaskRepository taskRepository;

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Course course = new Course();
        course.setName("First Course Java");
        course.setPrice(100.00);
        course.setDescription("Java course beginners friendly");
        course.setCategories(Set.of("Java", "Programming"));
        courseRepository.save(course);


        Course course2 = new Course();
        course2.setName("Second Course Java");
        course2.setPrice(50.00);
        course2.setDescription("Advanced Java course beginners friendly");
        course2.setCategories(Set.of("Java", "Programming"));
        courseRepository.save(course2);

        CourseModule module1 = new CourseModule("DataTypes", 100, course, 1);
        CourseModule module2 = new CourseModule("If-else", 40, course, 2 );
        CourseModule module3 = new CourseModule("For loop", 40, course, 3);
        CourseModule module4 = new CourseModule("While loop", 70, course2, 1);
        CourseModule module5 = new CourseModule("Functions", 200, course2, 2);

        courseModuleRepository.saveAll(List.of(module1, module2, module3, module4, module5));

        Task task = new VideoTask();
        task.setTitle("Task 1");
        task.setDescription("Task 1");
        task.setCourseModule(module1);
        module1.addTast(task);
        task.setTest(false);

        Task task2 = new VideoTask();
        task2.setTitle("Task 2");
        task2.setDescription("Task 2");
        task2.setCourseModule(module1);
        module1.addTast(task2);
        task2.setTest(false);

        Task task3 = new VideoTask();
        task3.setTitle("Task 3");
        task3.setDescription("Task 3");
        task3.setCourseModule(module2);
        module2.addTast(task3);
        task3.setTest(false);

        taskRepository.saveAll(List.of(task, task2, task3));
        courseModuleRepository.saveAll(List.of(module1, module2, module3, module4, module5));






    }
}
