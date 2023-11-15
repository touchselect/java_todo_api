package com.example.todoapi2.exception;

public class TodoNotFoundException extends RuntimeException {

    private long todoId;

    public TodoNotFoundException(long todoId){
        super("Todo(id = " + todoId + ") is not found.");
        this.todoId = todoId;
    }
}
