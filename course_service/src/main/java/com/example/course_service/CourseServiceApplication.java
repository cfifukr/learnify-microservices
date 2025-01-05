package com.example.course_service;

import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;
import com.example.course_service.repository.CourseModuleRepository;
import com.example.course_service.repository.CourseRepository;
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




    }
}
