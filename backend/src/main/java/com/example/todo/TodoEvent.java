package com.example.todo;

public class TodoEvent {
    private String type;
    private Todo todo;

    public TodoEvent() {}
    public TodoEvent(String type, Todo todo) {
        this.type = type;
        this.todo = todo;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Todo getTodo() { return todo; }
    public void setTodo(Todo todo) { this.todo = todo; }
}
