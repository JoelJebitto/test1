package com.example.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TodoEventConsumer {
    private final TodoRepository repository;

    public TodoEventConsumer(TodoRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "todos", groupId = "todo-consumers")
    public void consume(TodoEvent event) {
        Todo todo = event.getTodo();
        switch (event.getType()) {
            case "todo_created" -> repository.save(todo);
            case "todo_updated" -> repository.save(todo);
            case "todo_deleted" -> repository.deleteById(todo.getId());
        }
    }
}
