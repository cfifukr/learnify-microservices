package com.example.user_service;

import com.example.user_service.model.Role;
import com.example.user_service.model.User;
import com.example.user_service.repository.RoleRepository;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        User user1 = new User("Name", "Surname",
                "Email@gmail.com", "Keycloack1");
        userRepository.save(user1);

        User user2 = new User("Test", "Test",
                "vladyslav.dryk@gmail.com", "6a34a7ea-4cdb-4bea-956b-2da037f31c97");
        user1.addCourseEnrolled(2432432+"");
        user1.addCourseEnrolled(3+"");
        user1.addCourseEnrolled(354235249+"");
        userRepository.save(user2);
        userRepository.save(user1);


    }
}
