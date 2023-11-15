package com.example.todoapi2.service;

import com.example.todoapi2.model.Status;
import com.example.todoapi2.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    List<Todo> getTodoByStatus(Status status);
    List<Todo> getTodoByTitle(String title);
    Todo getTodoById(Long id);
    void createTodo(Todo todo);
    boolean updateTodo(Todo todo);
    boolean deleteTodoById(Long id);
    List<Todo> getTodoSortedById(String order);
    List<Todo> getTodoSortedByStatus(String order);
}
