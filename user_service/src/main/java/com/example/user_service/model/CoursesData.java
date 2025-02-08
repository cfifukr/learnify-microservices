package com.example.user_service.model;

import com.example.user_service.exception.CourseExceededLimitException;
import com.example.user_service.utils.StringListConverter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class CoursesData {

    private static int MAX_COURSES = 25;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @Convert(converter = StringListConverter.class)
    private final List<String> coursesEnrolledIds = new ArrayList<>();


    @Convert(converter = StringListConverter.class)
    private final  List<String> coursesChosenIds = new ArrayList<>();



    public CoursesData() {}

    public CoursesData(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public CoursesData(User user) {
        this.user = user;
    }

    public boolean addCourseEnrolled(String id) {
        return addCourse(id, coursesEnrolledIds);
    }

    public boolean addCourseChosen(String id) {
        return addCourse(id, coursesChosenIds);
    }

    public void deleteCourseEnrolled(String id) {
        coursesEnrolledIds.remove(id);
    }

    public void deleteCourseChosen(String id) {
        coursesChosenIds.remove(id);
    }

    public List<String> getCoursesEnrolledIds() {
        return new ArrayList<>(coursesEnrolledIds);
    }

    public List<String> getCoursesChosenIds() {
        return new ArrayList<>(coursesChosenIds);
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CoursesData that = (CoursesData) o;
        return Objects.equals(id, that.id) && Objects.equals(coursesEnrolledIds, that.coursesEnrolledIds)  && Objects.equals(coursesChosenIds, that.coursesChosenIds) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coursesEnrolledIds, coursesChosenIds);
    }

    @Override
    public String toString() {
        return "CoursesData{" +
                "id=" + id +
                ", coursesEnrolledIds=" + coursesEnrolledIds +
                ", coursesChosenIds=" + coursesChosenIds +
                '}';
    }


    private boolean addCourse(String id, List<String> courseList) {
        if (courseList.size() >= MAX_COURSES) {
            throw new CourseExceededLimitException("User with id: " + this.user.getId() +  " exceed limit of max courses");
        }
        courseList.add(id);
        courseList.remove("");
        return true;
    }
}
