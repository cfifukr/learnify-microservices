package com.example.user_service.configs;

import com.example.core.UserEnrolledEvent;
import com.example.core.UserEnrolledStatisticEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String servers;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    @Value("${spring.kafka.producer.acks}")
    private String acks;


    Map<String, Object> producerConfigs (){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        config.put(ProducerConfig.ACKS_CONFIG, acks);
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        return config;
    }

    // Kafka to Notification service
    @Bean
    ProducerFactory<String, UserEnrolledEvent> producerFactoryNotification(){
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, UserEnrolledEvent> kafkaTemplateNotification(){
        return new KafkaTemplate<String, UserEnrolledEvent>(producerFactoryNotification());
    }

    @Bean
    NewTopic createTopicNotificationEnrolledEvent(){
        return TopicBuilder
                .name("enrollment_for_course_notification_event")
                .partitions(3)
                .replicas(0)
                .build();
    }

    // Kafka to Statistic service
    @Bean
    ProducerFactory<String, UserEnrolledStatisticEvent> producerFactoryStatistic(){
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    KafkaTemplate<String, UserEnrolledStatisticEvent> kafkaTemplateStatistic(){
        return new KafkaTemplate<>(producerFactoryStatistic());
    }


    @Bean
    NewTopic createTopicStatisticEnrolledEvent(){
        return TopicBuilder
                .name("enrollment_for_course_statistic_event")
                .partitions(3)
                .replicas(0)
                .build();
    }
}
