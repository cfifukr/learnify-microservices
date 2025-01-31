package com.example.notification_service.event;

import com.example.core.UserEnrolledEvent;
import com.example.notification_service.services.MailNotificationService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "user_enrolled_for_course", groupId = "group1")
public class UserEnrolledEventHandler{
    private final MailNotificationService mailNotificationService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserEnrolledEventHandler(MailNotificationService mailNotificationService) {
        this.mailNotificationService = mailNotificationService;
    }

    @KafkaHandler
    public void handle(UserEnrolledEvent userEnrolledEvent){
        logger.info("**Kafka** Received a new UserEnrollmentEvent name - {}, email - {}",
                userEnrolledEvent.getName(), userEnrolledEvent.getEmail());
        try{
            mailNotificationService.sendEnrollmentOnCourseEmail(userEnrolledEvent);
            logger.info("Notification about enrollment was sent on email " + userEnrolledEvent.getEmail());

        }catch (MessagingException exception){
            logger.error("Notification to email {} about enrollment wasn`t sent. Error : {}",
                    userEnrolledEvent.getEmail(), exception.getMessage());
        }

    }

}
