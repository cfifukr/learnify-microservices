package com.example.user_service.dto.request;


import com.example.user_service.model.Role;
import com.example.user_service.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserCreateDto {
    private String username;

    private String name;
    private String surname;
    private String email;
    private String password;


    public UserCreateDto() {
    }

    public UserCreateDto( String username, String name, String surname, String email, String password) {
        this.username = username;

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User createUser(String keycloakId){
        return new User(this.getName(), this.getSurname(), this.getEmail(), keycloakId);
    }
}
