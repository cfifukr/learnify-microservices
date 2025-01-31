package com.example.notification_service.event;

import com.example.core.UserEnrolledEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "user_enrolled_for_course", groupId = "group1")
public class UserEnrolledEventHandler{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void handle(UserEnrolledEvent userEnrolledEvent){
        logger.info("**** Received a new UserEnrollmentEvent " + userEnrolledEvent);
    }

}
