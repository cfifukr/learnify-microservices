package com.example.user_service.service;


import com.example.user_service.dto.request.UserCreateDto;
import com.example.user_service.exception.RoleNotFoundException;
import com.example.user_service.model.Role;
import com.example.user_service.model.User;
import com.example.user_service.repository.RoleRepository;
import com.example.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public Optional<User> getUserById(Long id){
        if(id == null){
            return Optional.empty();
        }
        return userRepository.findById(id);
    }

    public Optional<User> getUserByKeycloakId(String id){
        if(id.isEmpty()){
            return Optional.empty();
        }

        return userRepository.findByKeycloakId(id);
    }

    public User createUser(UserCreateDto userCreateDto, String keycloakId){

        User user = userCreateDto.createUser(keycloakId);

        return userRepository.save(user);

    }


}
