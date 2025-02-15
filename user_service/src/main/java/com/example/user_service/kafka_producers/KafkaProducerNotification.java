package com.example.user_service.kafka_producers;

import com.example.core.UserEnrolledEvent;
import com.example.user_service.model.User;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaProducerNotification {
    private final KafkaTemplate<String, UserEnrolledEvent> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public KafkaProducerNotification(KafkaTemplate<String, UserEnrolledEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendEnrollmentToNotification(User user, String courseId) {
        String topic = "enrollment_for_course_notification_event";


        UserEnrolledEvent userEnrolledEvent = new UserEnrolledEvent();
        userEnrolledEvent.setName(user.getName());
        userEnrolledEvent.setSurname(user.getSurname());
        userEnrolledEvent.setEmail(user.getEmail());
        userEnrolledEvent.setCourseProgressId(courseId);

        CompletableFuture<SendResult<String, UserEnrolledEvent>> future =
                kafkaTemplate.send(topic, courseId, userEnrolledEvent);


        future.whenComplete((result, exception) -> {
            if (exception == null) {
                RecordMetadata metadata = result.getRecordMetadata();
                logger.info("UserEnrollment sent successfully to topic 'enrollment_for_course_notification_event' at partition [{}], offset [{}]",
                        metadata.partition(), metadata.offset());
            } else {
                logger.error("Failed to send UserEnrollment  to topic 'enrollment_for_course_notification_event' due to: {}",
                        exception.getMessage());
            }
        });

    }
}
