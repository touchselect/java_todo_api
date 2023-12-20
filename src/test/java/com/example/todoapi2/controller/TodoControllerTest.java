package com.example.todoapi2.controller;

import com.example.todoapi2.model.Status;
import com.example.todoapi2.model.Todo;
import com.example.todoapi2.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    private static Todo setUpTodo() {
        Todo todo = new Todo();
        todo.setId(999L);
        todo.setTitle("Test Todo");
        todo.setStatus(Status.valueOf("未着手"));
        todo.setDetails("hogehoge");
        return todo;
    }

    @Test
    void getAllTodos() {
        List<Todo> todos = Arrays.asList(
                new Todo(),
                new Todo(),
                new Todo()
        );

        when(todoService.getAllTodos()).thenReturn(todos);

        ResponseEntity<List<Todo>> response = todoController.getAllTodos();
        verify(todoService, times(1)).getAllTodos();

        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(response.getBody(), is(equalTo(todos)));
    }

    @Test
    void getTodoByStatus() {
    }

    @Test
    void getTodoByTitle() {
    }

    @Test
    void getTodoSortedById() {
    }

    @Test
    void getTodoSortedByIdDesc() {
    }

    @Test
    void getTodoSortedByStatus() {
    }

    @Test
    void getTodoSortedByStatusDesc() {
    }

    @Test
    void createTodo() {
        Todo todo = new Todo();
        doNothing().when(todoService).createTodo(todo);
        ResponseEntity<Todo> response = todoController.createTodo(todo);
        verify(todoService, times(1)).createTodo(any());
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
        assertThat(response.getHeaders().getLocation(), is(equalTo(URI.create("/todos/" + todo.getId()))));
    }

    @Test
    void getTodoById_Existing() {
        Todo todo = new Todo();
        when(todoService.getTodoById(anyLong())).thenReturn(todo);
        ResponseEntity<Todo> response = todoController.getTodoById(1L);
        verify(todoService, times(1)).getTodoById(1L);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(response.getBody(), is(equalTo(todo)));
    }

    @Test
    void getTodoById_notExisting() {
        when(todoService.getTodoById(anyLong())).thenReturn(null);
        ResponseEntity<Todo> response = todoController.getTodoById(anyLong());
        verify(todoService, times(1)).getTodoById(anyLong());
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
    }

    @Test
    void updateTodo() {

    }

    @Test
    void deleteTodo() {
    }
}