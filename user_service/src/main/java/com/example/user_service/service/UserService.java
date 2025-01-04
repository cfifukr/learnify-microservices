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
    private RoleRepository roleRepository;


    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

        Optional<Role> roleOpt = roleRepository.findRoleByRole(userCreateDto.getRole().toUpperCase().trim());
        if(roleOpt.isEmpty()){
            throw new RoleNotFoundException("This role doesnt exist");
        }

        User user = userCreateDto.createUser(keycloakId, roleOpt.get());

        return userRepository.save(user);

    }


}
