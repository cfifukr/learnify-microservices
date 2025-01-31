package com.example.core;


import java.util.Objects;

public class UserEnrolledEvent {
    private String courseProgressId;
    private String name;
    private String surname;
    private String email;

    public UserEnrolledEvent(String courseProgressId, String name, String surname, String email) {
        this.courseProgressId = courseProgressId;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public UserEnrolledEvent() {}

    public String getCourseProgressId() {
        return courseProgressId;
    }

    public void setCourseProgressId(String courseProgressId) {
        this.courseProgressId = courseProgressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEnrolledEvent that = (UserEnrolledEvent) o;
        return Objects.equals(courseProgressId, that.courseProgressId) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseProgressId, name, surname, email);
    }

    @Override
    public String toString() {
        return "UserEnrolledEvent{" +
                "courseProgressId='" + courseProgressId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
