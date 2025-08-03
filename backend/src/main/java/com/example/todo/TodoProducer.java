package com.example.todo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TodoProducer {
    private final KafkaTemplate<String, TodoEvent> kafkaTemplate;

    public TodoProducer(KafkaTemplate<String, TodoEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String type, Todo todo) {
        TodoEvent event = new TodoEvent(type, todo);
        kafkaTemplate.send(KafkaConfig.TODO_TOPIC, event);
    }
}
