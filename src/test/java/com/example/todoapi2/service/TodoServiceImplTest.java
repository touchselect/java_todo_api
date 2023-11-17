package com.example.todoapi2.service;

import com.example.todoapi2.exception.TodoNotFoundException;
import com.example.todoapi2.mapper.TodoMapper;
import com.example.todoapi2.model.Status;
import com.example.todoapi2.model.Todo;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.implementation.ExceptionMethod.throwing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {

    @InjectMocks
    private TodoServiceImpl todoService;

    @Mock
    private TodoMapper todoMapper;

    @Test
    void getAllTodos() {
        // todoMapper.getAllTodos()が呼ばれたとき、空のArrayListを返す
        when(todoMapper.getAllTodos()).thenReturn(new ArrayList<>());

        // ここでtodosには上記で指定した空のArrayListが入る
        List<Todo> todos = todoService.getAllTodos();

        // todoMapper.getAllTodos()が何回呼ばれたかチェック
        verify(todoMapper, times(1)).getAllTodos();

        // 本当にtodosの中は空のArrayListかどうかチェック
        assertThat(todos, is(empty()));
    }

    @Test
    void getTodoByStatus() {
    }

    @Test
    void getTodoByTitle() {
    }

    @Test
    void getTodoById_Existing() {
        when(todoMapper.getTodoById(1L)).thenReturn(new Todo());
        Todo result = todoService.getTodoById(1L);
        verify(todoMapper, times(1)).getTodoById(1L);
        assertThat(result, is(notNullValue()));
    }

    @Test
    void getTodoById_notExisting() {
        when(todoMapper.getTodoById(1L)).thenReturn(null);
        assertThrows(TodoNotFoundException.class, () -> todoService.getTodoById(1L));
        verify(todoMapper, times(1)).getTodoById(1L);
    }

    @Test
    void createTodo() {
        doNothing().when(todoMapper).insertTodo(any());
        Todo todo = new Todo();
        todoService.createTodo(todo);
        verify(todoMapper, times(1)).insertTodo(todo);
    }

    @Test
    void updateTodo() {
        doNothing().when(todoMapper).updateTodo(any());
        Todo todo = new Todo();
        todoService.updateTodo(todo);
        verify(todoMapper, times(1)).updateTodo(todo);
    }

    @Test
    void deleteTodoById() {
        doNothing().when(todoMapper).deleteTodoById(any());
        Long todoId = 1L;
        todoService.deleteTodoById(todoId);
        verify(todoMapper, times(1)).deleteTodoById(todoId);
    }

    @Test
    void getTodoSortedById() {
        List<Todo> unsortedTodos = Arrays.asList(
                new Todo(),
                new Todo(),
                new Todo()
        );

        List<Todo> sortedTodos = Arrays.asList(
                new Todo(),
                new Todo(),
                new Todo()
        );

        when(todoMapper.getTodoSortedById("asc")).thenReturn(unsortedTodos);

        // テスト対象のメソッドを呼び出し
        List<Todo> result = todoService.getTodoSortedById("asc");

        // モックのメソッドが正しく呼び出されたことを検証
        verify(todoMapper, times(1)).getTodoSortedById("asc");

        // 期待されるソート結果と実際の結果を比較
        assertThat(result, containsInRelativeOrder(sortedTodos.toArray()));
    }

    @Test
    void getTodoSortedByStatus() {
    }
}