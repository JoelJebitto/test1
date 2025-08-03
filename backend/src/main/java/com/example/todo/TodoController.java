package com.example.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoRepository repository;
    private final TodoProducer producer;

    public TodoController(TodoRepository repository, TodoProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @GetMapping
    public List<Todo> all() {
        return repository.findAll();
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        todo.setId(UUID.randomUUID());
        producer.sendEvent("todo_created", todo);
        return todo;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable UUID id, @RequestBody Todo todo) {
        todo.setId(id);
        producer.sendEvent("todo_updated", todo);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        Todo todo = new Todo();
        todo.setId(id);
        producer.sendEvent("todo_deleted", todo);
        return ResponseEntity.noContent().build();
    }
}
