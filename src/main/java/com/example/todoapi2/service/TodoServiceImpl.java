package com.example.todoapi2.service;

import com.example.todoapi2.mapper.TodoMapper;
import com.example.todoapi2.model.Status;
import com.example.todoapi2.model.Todo;
import com.example.todoapi2.exception.TodoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;

    @Override
    public List<Todo> getAllTodos() {
        return todoMapper.getAllTodos();
    }

    @Override
    public List<Todo> getTodoByStatus(Status status) {
        return todoMapper.getTodoByStatus(status);
    }

    @Override
    public List<Todo> getTodoByTitle(String title) {
        return todoMapper.getTodoByTitle(title);
    }

    @Override
    public Todo getTodoById(Long id) {
        Todo todo = todoMapper.getTodoById(id);
        if(todo == null){
            throw new TodoNotFoundException(id);
        }
        return todo;
    }

    @Override
    public void createTodo(Todo todo) {
        todoMapper.insertTodo(todo);
    }

    @Override
    public boolean updateTodo(Todo todo) {
        todoMapper.updateTodo(todo);
        return false;
    }

    @Override
    public boolean deleteTodoById(Long id) {
        todoMapper.deleteTodoById(id);
        return false;
    }

    @Override
    public List<Todo> getTodoSortedById(String order) {
        return todoMapper.getTodoSortedById(order);
    }

    @Override
    public List<Todo> getTodoSortedByStatus(String order) {
        return todoMapper.getTodoSortedByStatus(order);
    }
}
