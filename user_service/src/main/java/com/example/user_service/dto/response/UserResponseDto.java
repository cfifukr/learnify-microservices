package com.example.user_service.dto.response;

import com.example.user_service.model.User;
import jakarta.persistence.Column;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String keycloakId;
    private Double balance;
    private List<String> coursesEnrolled;
    private List<String> coursesChosen;


    public UserResponseDto(Long id, String name, String surname, String email, String keycloakId, Double balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.keycloakId = keycloakId;
        this.balance = balance;
    }

    public static UserResponseDto getDto(User user){
        UserResponseDto dto =  new UserResponseDto(user.getId(), user.getName(), user.getSurname(),
                user.getEmail(), user.getKeycloakId(), user.getBalance());
        dto.setCoursesEnrolled(user.getCoursesData().getCoursesEnrolledIds());
        dto.setCoursesChosen(user.getCoursesData().getCoursesChosenIds());
        return dto;
    }

}
