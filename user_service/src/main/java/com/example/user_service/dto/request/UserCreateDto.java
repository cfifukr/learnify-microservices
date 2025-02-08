package com.example.user_service.dto.request;


import com.example.user_service.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;


    public User createUser(String keycloakId){
        return new User(this.getName(), this.getSurname(), this.getEmail(), keycloakId);
    }
}
