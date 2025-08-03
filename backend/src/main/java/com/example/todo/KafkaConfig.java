package com.example.todo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    public static final String TODO_TOPIC = "todos";

    @Bean
    public NewTopic todoTopic() {
        return TopicBuilder.name(TODO_TOPIC).partitions(1).replicas(1).build();
    }
}
