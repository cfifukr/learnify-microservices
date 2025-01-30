package com.example.user_service.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEnrolledEvent {
    private String courseName;
    private String userName;
    private String userSurname;
    private String email;
    private String description;
}
