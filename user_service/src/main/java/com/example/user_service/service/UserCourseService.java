package com.example.user_service.service;

import com.example.user_service.exception.EnrollmentLimitExceededException;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;



// WITH THIS SERVICE WORKS ONLY COURSE-MICROSERVICE
@Service
public class UserCourseService {
    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    private final JwtUtils jwtUtils = new JwtUtils();


    private final String baseUrlCourse = "http://COURSE-SERVICE";

    @Value("${url.course-service.course-progress}")
    private String progressUrl;

    public UserCourseService(UserRepository userRepository,
                             RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate  = restTemplate;
    }


    public List<String> enrollUserForCourse(String token, String courseId) {

        String keycloakId = jwtUtils.extractKeycloakId(token);
        User user = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + keycloakId + " not found"));


        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        String url = new StringBuilder()
                .append(baseUrlCourse)
                .append(progressUrl)
                .append("/course/")
                .append(courseId).toString();
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            System.out.println(response.getBody());
            user.addCourseEnrolled(response.getBody());


        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return userRepository.save(user).getCoursesEnrolledIds();

    }


    public List<String> deleteUserFromCourse(Long userId , String courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        user.deleteCourseEnrolled(courseId);

        return userRepository.save(user).getCoursesEnrolledIds();

    }
}
