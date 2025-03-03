package com.example.statistic_service.event;

import com.example.core.UserEnrolledStatisticEvent;
import com.example.statistic_service.service.CourseStatService;
import com.example.statistic_service.service.YearStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;


@Component
@KafkaListener(topics = "enrollment_for_course_statistic_event", groupId = "group-2")
public class UserEnrolledStatisticEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(UserEnrolledStatisticEventHandler.class);
    private final YearStatService yearStatService;

    public UserEnrolledStatisticEventHandler(YearStatService yearStatService) {
        this.yearStatService = yearStatService;
    }

    @KafkaHandler
    public void userEnrolledForCourseStatisticEvent(UserEnrolledStatisticEvent userEnrolledEvent) {
        logger.info("**Kafka** Received a new UserEnrollmentStatisticEvent courseId - {}, studentId - {}",
                userEnrolledEvent.getCourseId(), userEnrolledEvent.getStudentKeycloakId());
        try{
            yearStatService.incrementCourseStat(userEnrolledEvent.getCourseId(), Month.of(LocalDate.now().getMonthValue()));
            logger.info("Statistic of course with id {} has been updated successfully", userEnrolledEvent.getCourseId());
        }catch (MessagingException exception){
            logger.error(exception.getMessage());
        }
    }



}
