package com.example.user_service.service;


import com.example.user_service.dto.request.UserCreateDto;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.model.User;

import com.example.user_service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = true)
    public User getUserById(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        logger.info("User found with id: {}", id);
        return user;
    }

    @Transactional(readOnly = true)
    public User getUserByKeycloakId(String keycloakId){

        User user = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new UserNotFoundException("User not found with keycloakId: " + keycloakId));
        logger.info("User found with keycloakId: {}",  keycloakId);
        return user;
    }

    @Transactional
    public User createUser(UserCreateDto userCreateDto, String keycloakId){

        User user = userCreateDto.createUser(keycloakId);
        userRepository.save(user);
        logger.info("User with id: {} and keycloakId: {} was saved to database", user.getId(), user.getKeycloakId());
        return user;

    }


}
