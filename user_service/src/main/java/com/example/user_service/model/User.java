package com.example.user_service.model;

import com.example.user_service.utils.StringListConverter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String keycloakId;
    private Double balance;

    @Convert(converter = StringListConverter.class)
    private List<String> coursesEnrolledIds = new ArrayList<>();



    public User() {
    }

    public User(String name, String surname, String email, String keycloakId) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.keycloakId = keycloakId;
        this.balance = 0.00;
    }



    public boolean addCourseEnrolled(String id){
        if(coursesEnrolledIds.size() >= 25){return false;}

        coursesEnrolledIds.add(id);

        return true;

    }

    public void deleteCourseEnrolled(String id){
        coursesEnrolledIds.remove(id);
    }


    public List<String> getCoursesEnrolledIds() {
        return coursesEnrolledIds;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", keycloakId='" + keycloakId + '\'' +
                ", balance=" + balance +
                '}';
    }
}
