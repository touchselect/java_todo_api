package com.example.todoapi2.service;

import com.example.todoapi2.exception.TodoNotFoundException;
import com.example.todoapi2.mapper.TodoMapper;
import com.example.todoapi2.model.Status;
import com.example.todoapi2.model.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // テスト用のダミーTodoリストを生成するメソッド
    private static List<Todo> getTodoList() {
        List<Todo> todoList = Arrays.asList(
                new Todo(),
                new Todo(),
                new Todo()
        );
        return todoList;
    }

    @Test
    void getAllTodos() {
        // todoMapper.getAllTodos()が呼ばれたとき、空のArrayListを返すようにモック設定
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
        // todoMapper.getTodoByStatus(Status.進行中)が呼ばれたとき、空のArrayListを返すようにモック設定
        when(todoMapper.getTodoByStatus(Status.進行中)).thenReturn(new ArrayList<>());
        List<Todo> todos = todoService.getTodoByStatus(Status.進行中);
        verify(todoMapper, times(1)).getTodoByStatus(Status.進行中);
        assertThat(todos, is(empty()));
    }

    @Test
    void getTodoByTitle() {
        // todoMapper.getTodoByTitle(anyString())が呼ばれたとき、空のArrayListを返すようにモック設定
        when(todoMapper.getTodoByTitle(anyString())).thenReturn(new ArrayList<>());
        List<Todo> todos = todoMapper.getTodoByTitle(anyString());
        verify(todoMapper, times(1)).getTodoByTitle(anyString());
        assertThat(todos, is(empty()));
    }

    @Test
    void getTodoById_Existing() {
        // todoMapper.getTodoById(1L)が呼ばれたとき、新しいTodoオブジェクトを返すようにモック設定
        when(todoMapper.getTodoById(1L)).thenReturn(new Todo());
        Todo result = todoService.getTodoById(1L);
        verify(todoMapper, times(1)).getTodoById(1L);
        assertThat(result, is(notNullValue()));
    }

    @Test
    void getTodoById_notExisting() {
        // todoMapper.getTodoById(1L)が呼ばれたとき、nullを返すようにモック設定
        when(todoMapper.getTodoById(1L)).thenReturn(null);
        // TodoNotFoundExceptionがスローされることを確認
        assertThrows(TodoNotFoundException.class, () -> todoService.getTodoById(1L));
        verify(todoMapper, times(1)).getTodoById(1L);
    }

    @Test
    void createTodo() {
        // todoMapper.insertTodo(any())が呼ばれたとき、任意のTodoオブジェクトを返すようにモック設定
        when(todoMapper.insertTodo(any())).thenReturn(anyInt());
        Todo todo = new Todo();
        todoService.createTodo(todo);
        verify(todoMapper, times(1)).insertTodo(todo);
    }

    @Test
    void updateTodo() {
        // todoMapper.updateTodo(any())が呼ばれたとき、何もしないようにモック設定
        doNothing().when(todoMapper).updateTodo(any());
        Todo todo = new Todo();
        todoService.updateTodo(todo);
        verify(todoMapper, times(1)).updateTodo(todo);
    }

    @Test
    void deleteTodoById() {
        // todoMapper.deleteTodoById(any())が呼ばれたとき、何もしないようにモック設定
        doNothing().when(todoMapper).deleteTodoById(any());
        Long todoId = 1L;
        todoService.deleteTodoById(todoId);
        verify(todoMapper, times(1)).deleteTodoById(todoId);
    }

    @Test
    void getTodoSortedById() {
        List<Todo> unsortedTodos = getTodoList();

        List<Todo> sortedTodos = getTodoList();

        // todoMapper.getTodoSortedById()が呼ばれたとき、unsortedTodosを返すようにモック設定
        when(todoMapper.getTodoSortedById()).thenReturn(unsortedTodos);

        // テスト対象のメソッドを呼び出し
        List<Todo> result = todoService.getTodoSortedById();

        // モックのメソッドが正しく呼び出されたことを検証
        verify(todoMapper, times(1)).getTodoSortedById();

        // 期待されるソート結果と実際の結果を比較
        assertThat(result, containsInRelativeOrder(sortedTodos.toArray()));
    }

    @Test
    void getTodoSortedByStatus() {
        List<Todo> unsortedTodos = getTodoList();

        List<Todo> sortedTodos = getTodoList();

        // todoMapper.getTodoSortedByStatus()が呼ばれたとき、unsortedTodosを返すようにモック設定
        when(todoMapper.getTodoSortedByStatus()).thenReturn(unsortedTodos);

        // テスト対象のメソッドを呼び出し
        List<Todo> result = todoService.getTodoSortedByStatus();

        // モックのメソッドが正しく呼び出されたことを検証
        verify(todoMapper, times(1)).getTodoSortedByStatus();

        // 期待されるソート結果と実際の結果を比較
        assertThat(result, containsInRelativeOrder(sortedTodos.toArray()));
    }

    @Test
    void getTodoSortedByIdDesc() {
        List<Todo> unsortedTodos = getTodoList();

        List<Todo> sortedTodos = getTodoList();

        // todoMapper.getTodoSortedById()が呼ばれたとき、unsortedTodosを返すようにモック設定
        when(todoMapper.getTodoSortedByIdDesc()).thenReturn(unsortedTodos);

        // テスト対象のメソッドを呼び出し
        List<Todo> result = todoService.getTodoSortedByIdDesc();

        // モックのメソッドが正しく呼び出されたことを検証
        verify(todoMapper, times(1)).getTodoSortedByIdDesc();

        // 期待されるソート結果と実際の結果を比較
        assertThat(result, containsInRelativeOrder(sortedTodos.toArray()));
    }

    @Test
    void getTodoSortedByStatusDesc() {
        List<Todo> unsortedTodos = getTodoList();

        List<Todo> sortedTodos = getTodoList();

        // todoMapper.getTodoSortedByStatus()が呼ばれたとき、unsortedTodosを返すようにモック設定
        when(todoMapper.getTodoSortedByStatusDesc()).thenReturn(unsortedTodos);

        // テスト対象のメソッドを呼び出し
        List<Todo> result = todoService.getTodoSortedByStatusDesc();

        // モックのメソッドが正しく呼び出されたことを検証
        verify(todoMapper, times(1)).getTodoSortedByStatusDesc();

        // 期待されるソート結果と実際の結果を比較
        assertThat(result, containsInRelativeOrder(sortedTodos.toArray()));
    }
}
