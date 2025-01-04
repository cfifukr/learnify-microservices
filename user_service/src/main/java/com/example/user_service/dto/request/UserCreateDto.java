package com.example.user_service.dto.request;


import com.example.user_service.model.Role;
import com.example.user_service.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserCreateDto {

    private String name;
    private String surname;
    private String email;
    private String role;

    public UserCreateDto() {
    }

    public UserCreateDto(String name, String surname, String email, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User createUser(String keycloakId, Role role){
        return new User(this.getName(), this.getSurname(), this.getEmail(), keycloakId, role);
    }
}
