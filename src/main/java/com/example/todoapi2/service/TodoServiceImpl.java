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
        int updatedRows = todoMapper.updateTodo(todo);
        return updatedRows > 0;
    }

    @Override
    public boolean deleteTodoById(Long id) {
        int deletedRows = todoMapper.deleteTodoById(id);
        return deletedRows > 0;
    }

    @Override
    public List<Todo> getTodoSortedById() {
        return todoMapper.getTodoSortedById();
    }

    @Override
    public List<Todo> getTodoSortedByIdDesc() {
        return todoMapper.getTodoSortedByIdDesc();
    }

    @Override
    public List<Todo> getTodoSortedByStatus() {
        return todoMapper.getTodoSortedByStatus();
    }

    @Override
    public List<Todo> getTodoSortedByStatusDesc() {
        return todoMapper.getTodoSortedByStatusDesc();
    }
}
