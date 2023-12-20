package com.example.todoapi2.mapper;

import com.example.todoapi2.model.Status;
import com.example.todoapi2.model.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {

    List<Todo> getAllTodos();

    List<Todo> getTodoByStatus(Status status);

    List<Todo> getTodoByTitle(String title);

    Todo getTodoById(Long id);

    int insertTodo(Todo todo);

    int updateTodo(Todo todo);

    int deleteTodoById(Long id);

    List<Todo> getTodoSortedById();

    List<Todo> getTodoSortedByStatus();

    List<Todo> getTodoSortedByIdDesc();

    List<Todo> getTodoSortedByStatusDesc();
}
