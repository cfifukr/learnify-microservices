package com.example.user_service.service;

import com.example.core.UserEnrolledEvent;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.utils.JwtUtils;
import jakarta.transaction.Transactional;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import java.util.concurrent.CompletableFuture;


// WITH THIS SERVICE WORKS ONLY COURSE-MICROSERVICE
@Service
public class UserCourseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

    KafkaTemplate<String, UserEnrolledEvent> kafkaTemplate;

    private final RestTemplate restTemplate;

    private final JwtUtils jwtUtils = new JwtUtils();


    private final String baseUrlCourse = "http://COURSE-SERVICE";

    @Value("${url.course-service.course-progress}")
    private String progressUrl;

    public UserCourseService(UserRepository userRepository,
                             RestTemplate restTemplate,
                             KafkaTemplate<String, UserEnrolledEvent> kafkaTemplate) {
        this.userRepository = userRepository;
        this.restTemplate  = restTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Transactional
    public List<String> enrollUserForCourse(String token, String courseId) {

        // Getting User object
        String keycloakId = jwtUtils.extractKeycloakId(token);
        User user = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + keycloakId + " not found"));

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
            user.addCourseEnrolled(response.getBody());

            //sending notification on email about enrollment on course
            UserEnrolledEvent userEnrolledEvent = new UserEnrolledEvent();
            userEnrolledEvent.setName(user.getName());
            userEnrolledEvent.setSurname(user.getSurname());
            userEnrolledEvent.setEmail(user.getEmail());
            userEnrolledEvent.setCourseProgressId(courseId);

            CompletableFuture<SendResult<String, UserEnrolledEvent>> future =
                    kafkaTemplate.send("user_enrolled_for_course", courseId, userEnrolledEvent);


            future.whenComplete((result, exception) -> {
                if (exception == null) {
                    RecordMetadata metadata = result.getRecordMetadata();
                    logger.info("UserEnrollment sent successfully to topic 'user_enrolled_for_course' at partition [{}], offset [{}]",
                              metadata.partition(), metadata.offset());
                } else {
                    logger.error("Failed to send UserEnrollment  to topic 'user_enrolled_for_course' due to: {}",
                            exception.getMessage());
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return userRepository.save(user).getCoursesEnrolledIds();

    }


    @Transactional
    public List<String> deleteUserFromCourse(Long userId , String courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        user.deleteCourseEnrolled(courseId);

        return userRepository.save(user).getCoursesEnrolledIds();

    }
}
