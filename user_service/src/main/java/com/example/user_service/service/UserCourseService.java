package com.example.user_service.service;

import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.kafka_producers.KafkaProducerNotification;
import com.example.user_service.kafka_producers.KafkaProducerStatistic;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.utils.JwtUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;



// WITH THIS SERVICE WORKS ONLY COURSE-MICROSERVICE
@Service
public class UserCourseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    private final JwtUtils jwtUtils;

    private final KafkaProducerNotification kafkaNotification;
    private final KafkaProducerStatistic kafkaStatistic;


    @Value("${url.course-service.course-progress}")
    private String progressUrl;

    public UserCourseService(UserRepository userRepository,
                             RestTemplate restTemplate,
                             JwtUtils jwtUtils,
                             KafkaProducerNotification kafkaNotification,
                             KafkaProducerStatistic kafkaStatistic) {
        this.kafkaStatistic = kafkaStatistic;
        this.userRepository = userRepository;
        this.restTemplate  = restTemplate;
        this.jwtUtils = jwtUtils;
        this.kafkaNotification = kafkaNotification;
    }


    @Transactional
    public List<String> enrollUserForCourse(String token, String courseId) {
        String baseUrlCourse = "http://COURSE-SERVICE";


        // Getting User object
        String keycloakId = jwtUtils.extractKeycloakId(token);
        User user = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new UserNotFoundException("User not found with keycloakId: " + keycloakId));

        // HttpRequest to  Course service to make CourseProgress ang return its id
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

            //add returned id of course progress to user
            System.out.println(response.getBody());
            user.getCoursesData().addCourseEnrolled(response.getBody());

            kafkaNotification.sendEnrollmentToNotification(user, courseId);
            //TODO: add creatorKeycloakId to event or remove this field for event
            kafkaStatistic.sendEnrollmentToStatistic(user.getKeycloakId(), "",Long.valueOf(courseId));

        } catch (Exception ex) {
            return null;
        }

        return userRepository.save(user).getCoursesData().getCoursesEnrolledIds();

    }


    @Transactional
    public List<String> deleteUserFromCourse(Long userId , String courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        user.getCoursesData().deleteCourseEnrolled(courseId);

        return userRepository.save(user).getCoursesData().getCoursesEnrolledIds();

    }
}
