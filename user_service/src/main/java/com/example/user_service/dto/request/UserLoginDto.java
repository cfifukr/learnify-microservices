package com.example.user_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginDto {

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 15, message = "Username length must be between 4 and 15")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 15, message = "Password length must be between 6 and 15")
    private String password;
}
