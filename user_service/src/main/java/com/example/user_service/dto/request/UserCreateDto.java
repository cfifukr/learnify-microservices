package com.example.user_service.dto.request;


import com.example.user_service.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 15, message = "Username length must be between 4 and 15")
    private String username;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 15, message = "Name length must must be between 2 and 15")
    private String name;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 15, message = "Last length size must be between 2 and 15")
    private String surname;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 15, message = "Password length must be between 6 and 15")
    private String password;


    public User createUser(String keycloakId){
        return new User(this.getName(), this.getSurname(), this.getEmail(), keycloakId);
    }
}
