package com.example.todoapi2.service;

import com.example.todoapi2.mapper.TodoMapper;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {

    @InjectMocks
    private TodoServiceImpl todoService;

    @Mock
    private TodoMapper todoMapper;

    @Test
    void getAllTodos() {
    }

    @Test
    void getTodoByStatus() {
    }

    @Test
    void getTodoByTitle() {
    }

    @Test
    void getTodoById() {
    }

    @Test
    void createTodo() {
    }

    @Test
    void updateTodo() {
    }

    @Test
    void deleteTodoById() {
    }

    @Test
    void getTodoSortedById() {
    }

    @Test
    void getTodoSortedByStatus() {
    }
}