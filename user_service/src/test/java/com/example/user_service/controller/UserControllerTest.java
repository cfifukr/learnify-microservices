package com.example.user_service.controller;

import com.example.user_service.dto.request.UserCreateDto;
import com.example.user_service.dto.request.UserLoginDto;
import com.example.user_service.dto.response.TokenResponse;
import com.example.user_service.dto.response.UserResponseDto;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.repository.UserRepositoryTest;
import com.example.user_service.service.KeycloakService;
import com.example.user_service.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @TestConfiguration
    static class MockConfig {
        @Bean
        public UserService myService() {
            return mock(UserService.class);
        }

        @Bean
        public KeycloakService keycloakService() {
            return mock(KeycloakService.class);
        }

        @Bean
        public UserRepository userRepository() {
            return mock(UserRepository.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeycloakService keycloakService;

    @Autowired
    private UserService userService;

    private User mockUser;
    private String keycloakId;
    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        keycloakId = UUID.randomUUID().toString();
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Test");
        mockUser.setSurname("TestSurname");
        mockUser.setEmail("test@email.com");
        mockUser.setKeycloakId(keycloakId);
        mockUser.setBalance(0.00);
    }

    @Test
    void getUserById_validId_shouldReturnUser() throws Exception {
        when(userService.getUserById(1L)).thenReturn(mockUser);

        mockMvc.perform(get("/api/v1/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.surname").value("TestSurname"))
                .andExpect(jsonPath("$.email").value("test@email.com"))
                .andExpect(jsonPath("$.keycloakId").value(keycloakId))
                .andExpect(jsonPath("$.balance").value(0.00));
    }


    @Test
    void getUserById_notExistedId_shouldReturnExceptionMessage() throws Exception {
        when(userService.getUserById(1L)).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get("/api/v1/user/1"))
                .andExpect(status().isNotFound());

    }


    @Test
    void getUserByKeycloakId_validId_shouldReturnUser() throws Exception {
        when(userService.getUserByKeycloakId(keycloakId)).thenReturn(mockUser);

        mockMvc.perform(get("/api/v1/user/keycloak/" + keycloakId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.surname").value("TestSurname"))
                .andExpect(jsonPath("$.email").value("test@email.com"))
                .andExpect(jsonPath("$.keycloakId").value(keycloakId))
                .andExpect(jsonPath("$.balance").value(0.00));
    }


    @Test
    void getUserByKeycloakId_notExistedId_shouldReturnExceptionMessage() throws Exception {
        when(userService.getUserByKeycloakId(keycloakId)).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get("/api/v1/user/keycloak/" + keycloakId))
                .andExpect(status().isNotFound());

    }

    @Test
    void register_ValidRequest_ShouldReturnUser() throws Exception {
        UserCreateDto validUserDto = new UserCreateDto();
        validUserDto.setUsername("testuser");
        validUserDto.setEmail("test@example.com");
        validUserDto.setName("John");
        validUserDto.setSurname("Doe");
        validUserDto.setPassword("StrongPass123");

        UserResponseDto mockResponseDto = UserResponseDto.getDto(mockUser);

        when(keycloakService.registerUser(
                anyString(), anyString(), anyString(), anyString(), anyString()
        )).thenReturn("keycloak-123");

        when(userService.createUser(any(UserCreateDto.class), anyString()))
                .thenReturn(mockUser);

        mockMvc.perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(validUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockResponseDto.getId()))
                .andExpect(jsonPath("$.email").value(mockResponseDto.getEmail()))
                .andExpect(jsonPath("$.name").value(mockResponseDto.getName()))
                .andExpect(jsonPath("$.surname").value(mockResponseDto.getSurname()))
                .andExpect(jsonPath("$.keycloakId").value(mockResponseDto.getKeycloakId()))
                .andExpect(jsonPath("$.balance").value(0.00));

        verify(keycloakService).registerUser(
                eq(validUserDto.getUsername()),
                eq(validUserDto.getEmail()),
                eq(validUserDto.getName()),
                eq(validUserDto.getSurname()),
                eq(validUserDto.getPassword())
        );

        verify(userService).createUser(any(UserCreateDto.class), eq("keycloak-123"));
    }

    @Test
    void register_InvalidRequest_ShouldReturnBadRequest() throws Exception {
        UserCreateDto invalidDto = new UserCreateDto();
        invalidDto.setUsername("t");
        invalidDto.setEmail("t");
        invalidDto.setName("t");
        invalidDto.setName("p");

        mockMvc.perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.surname").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.password").exists());

        verifyNoInteractions(keycloakService);
        verifyNoInteractions(userService);
    }

    @Test
    void login_ValidCredentials_ShouldReturnTokenResponse() throws Exception {
        UserLoginDto loginDto = new UserLoginDto();
        loginDto.setUsername("validUser");
        loginDto.setPassword("validPassword");

        TokenResponse mockResponse = new TokenResponse();
        mockResponse.setAccessToken("access_token");
        mockResponse.setRefreshToken("refresh_token");
        mockResponse.setExpiresIn(3600);
        mockResponse.setRefreshExpiresIn(7200);

        when(keycloakService.getToken(loginDto.getUsername(), loginDto.getPassword()))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("access_token"))
                .andExpect(jsonPath("$.refreshToken").value("refresh_token"))
                .andExpect(jsonPath("$.expiresIn").value(3600))
                .andExpect(jsonPath("$.refreshExpiresIn").value(7200));

        verify(keycloakService, times(1)).getToken(loginDto.getUsername(), loginDto.getPassword());
    }

    @Test
    void login_InvalidRequest_ShouldReturnBadRequest() throws Exception {
        UserLoginDto invalidDto = new UserLoginDto();

        mockMvc.perform(post("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.password").exists());
    }

    @Test
    void login_WrongCredentials_ShouldReturnUnauthorized() throws Exception {
        UserLoginDto loginDto = new UserLoginDto();
        loginDto.setUsername("invalidUser");
        loginDto.setPassword("wrongPassword");

        when(keycloakService.getToken(loginDto.getUsername(), loginDto.getPassword()))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        mockMvc.perform(post("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid username or password"));

        verify(keycloakService, times(1)).getToken(loginDto.getUsername(), loginDto.getPassword());
    }

}
