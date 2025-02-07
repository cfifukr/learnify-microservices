package com.example.user_service.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginDto {
    private String username;
    private String password;
}
