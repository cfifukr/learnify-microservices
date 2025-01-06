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
        Role role = new Role("ROLE_USER");
        roleRepository.save(role);

        User user1 = new User("Name", "Surname",
                "Email@gmail.com", "Keycloack1", role);
        userRepository.save(user1);

        User user2 = new User("Name2", "Surname2",
                "Email2@gmail.com", "Keycloack2", role);
        userRepository.save(user2);


    }
}
