package com.example.user_service.service;

import com.example.user_service.dto.request.UserCreateDto;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Name");
        user.setSurname("Surname");
        user.setEmail("email@gmail.com");
        user.setKeycloakId("keycloakId");
        user.setBalance(0.00);
    }


    @Test
    void getUserById_validId_shouldReturnUser() {

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

        User userFound = userService.getUserById(1L);

        assertNotNull(userFound, "User should not be null");
        assertEquals(user, userFound, "User should be equal");
        assertEquals(user.getId(), userFound.getId(), "User id should be equal");
        assertEquals(user.getName(), userFound.getName(), "User name should be equal");
        assertEquals(user.getSurname(), userFound.getSurname(), "User Surname should be equal");
        assertEquals(user.getEmail(), userFound.getEmail(), "User email should be equal");
        assertEquals(user.getBalance(), userFound.getBalance(), "User Balance should be equal");
        assertEquals(user.getKeycloakId(), userFound.getKeycloakId(), "User KeycloakId should be equal");
        verify(userRepository).findById(any(Long.class));

    }

    @Test
    void getUserById_notExistedId_shouldThrowUserNotFoundException() {

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(1L), "User should not be found");
        verify(userRepository).findById(any(Long.class));

    }

    @Test
    void getUserByKeycloakId_validId_shouldReturnUser() {

        when(userRepository.findByKeycloakId(anyString())).thenReturn(Optional.of(user));

        User userFound = userService.getUserByKeycloakId("keycloakId");

        assertNotNull(userFound, "User should not be null");
        assertEquals(user, userFound, "User should be equal");
        assertEquals(user.getId(), userFound.getId(), "User id should be equal");
        assertEquals(user.getName(), userFound.getName(), "User name should be equal");
        assertEquals(user.getSurname(), userFound.getSurname(), "User Surname should be equal");
        assertEquals(user.getEmail(), userFound.getEmail(), "User email should be equal");
        assertEquals(user.getBalance(), userFound.getBalance(), "User Balance should be equal");
        assertEquals(user.getKeycloakId(), userFound.getKeycloakId(), "User KeycloakId should be equal");
        verify(userRepository).findByKeycloakId(anyString());
    }

    @Test
    void getUserByKeycloakId_notExistedId_shouldThrowUserNotFoundException() {

        when(userRepository.findByKeycloakId(any(String.class))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.getUserByKeycloakId("keycloakId"), "User should not be found");
        verify(userRepository).findByKeycloakId(anyString());

    }


    @Test
    void createUser_validUser_shouldSaveUserAndUser() {
        String keycloakId = "keycloakId";

        UserCreateDto userCreateDto = mock(UserCreateDto.class);
        User user = new User();
        user.setId(1L);
        user.setKeycloakId(keycloakId);

        when(userCreateDto.createUser(keycloakId)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(userCreateDto, keycloakId);

        assertNotNull(createdUser);
        assertEquals(1L, createdUser.getId());
        assertEquals(keycloakId, createdUser.getKeycloakId());

        verify(userRepository).save(user);

    }
}





