package com.example.user_service.kafka_producers;

import com.example.core.UserEnrolledEvent;
import com.example.core.UserEnrolledStatisticEvent;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaProducerStatistic {
    private final KafkaTemplate<String, UserEnrolledStatisticEvent> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(KafkaProducerStatistic.class);


    public KafkaProducerStatistic(KafkaTemplate<String, UserEnrolledStatisticEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendEnrollmentToStatistic(String studentKeycloakId,
                                          String creatorKeycloakId,
                                          Long courseId) {
        String topic = "enrollment_for_course_statistic_event";

        UserEnrolledStatisticEvent enrolledEvent = new UserEnrolledStatisticEvent();
        enrolledEvent.setStudentKeycloakId(studentKeycloakId);
        enrolledEvent.setCreatorKeycloakId(creatorKeycloakId);
        enrolledEvent.setCourseId(courseId);


        CompletableFuture<SendResult<String, UserEnrolledStatisticEvent>> future =
                kafkaTemplate.send(topic, courseId.toString(), enrolledEvent);


        future.whenComplete((result, exception) -> {
            if (exception == null) {
                RecordMetadata metadata = result.getRecordMetadata();
                logger.info("UserEnrollment sent successfully to topic 'enrollment_for_course_statistic_event' at partition [{}], offset [{}]",
                        metadata.partition(), metadata.offset());
            } else {
                logger.error("Failed to send UserEnrollment  to topic 'enrollment_for_course_statistic_event' due to: {}",
                        exception.getMessage());
            }
        });
    }
}
