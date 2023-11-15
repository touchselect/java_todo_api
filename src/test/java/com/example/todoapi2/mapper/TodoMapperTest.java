package com.example.todoapi2.mapper;

import com.example.todoapi2.model.Status;
import com.example.todoapi2.model.Todo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
class TodoMapperTest {

    @Autowired
    private TodoMapper todoMapper;

    @Test
    void getAllTodos() {
        List<Todo> todos = todoMapper.getAllTodos();
        assertNotNull(todos);
    }

    @Test
    void getTodoByStatus() {
        List<Todo> todos1 = todoMapper.getTodoByStatus(Status.未着手);
        List<Todo> todos2 = todoMapper.getTodoByStatus(Status.進行中);
        List<Todo> todos3 = todoMapper.getTodoByStatus(Status.完了);

        assertNotNull(todos1);
        assertNotNull(todos2);
        assertNotNull(todos3);
    }

    @Test
    void getTodoByTitle() {
        List<Todo> matchAP = todoMapper.getTodoByTitle("ap");
        List<Todo> matchHoge = todoMapper.getTodoByTitle("hoge");

        assertThat(matchAP, hasSize(1));
        assertThat(matchHoge.get(0).getTitle(), equalTo("hoge"));
    }

    @Test
    void getTodoById() {
        Todo todo = todoMapper.getTodoById(1L);
        assertThat(todo.getTitle(), equalTo("タスク１"));
    }

    @Test
    void insertTodo() {
        Todo todo = new Todo();
        todo.setTitle("Todo to Insert");
        todo.setStatus(Status.valueOf("未着手"));
        todo.setDetails("hogehoge");

        todoMapper.insertTodo(todo);
        assertThat(todo.getId(), is(notNullValue()));

        Todo insertedTodo = todoMapper.getTodoById(todo.getId());
        assertThat(insertedTodo, is(notNullValue()));
        assertThat(insertedTodo.getTitle(), is(equalTo("Todo to Insert")));
    }

    @Test
    void updateTodo() {
        Todo todo = new Todo();

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