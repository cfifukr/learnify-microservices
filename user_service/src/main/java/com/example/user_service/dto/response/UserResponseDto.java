package com.example.user_service.dto.response;

import com.example.user_service.model.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String keycloakId;
    private Double balance;

    public UserResponseDto() {
    }

    public UserResponseDto(Long id, String name, String surname, String email, String keycloakId, Double balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.keycloakId = keycloakId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public static UserResponseDto getDto(User user){
        return new UserResponseDto(user.getId(), user.getName(), user.getSurname(),
                user.getEmail(), user.getKeycloakId(), user.getBalance());
    }

}
