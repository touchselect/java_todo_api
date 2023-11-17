package com.example.todoapi2.mapper;

import com.example.todoapi2.model.Status;
import com.example.todoapi2.model.Todo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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

    // テスト用のTodoオブジェクトをセットアップするメソッド
    private static Todo setUpTodo() {
        Todo todo = new Todo();
        todo.setTitle("Test Todo");
        todo.setStatus(Status.valueOf("未着手"));
        todo.setDetails("hogehoge");
        return todo;
    }

    // 全てのTodoを取得するテスト
    @Test
    void getAllTodos() {
        // マッパーから全てのTodoを取得
        List<Todo> todos = todoMapper.getAllTodos();
        // 取得されたリストがnullでないことを確認
        assertThat(todos, is(notNullValue()));
    }

    // ステータスに基づいてTodoを取得するテスト
    @Test
    void getTodoByStatus() {
        // 各ステータスに基づいてTodoを取得
        List<Todo> todos1 = todoMapper.getTodoByStatus(Status.未着手);
        List<Todo> todos2 = todoMapper.getTodoByStatus(Status.進行中);
        List<Todo> todos3 = todoMapper.getTodoByStatus(Status.完了);

        // 取得されたリストがnullでないことを確認
        assertThat(todos1, is(notNullValue()));
        assertThat(todos2, is(notNullValue()));
        assertThat(todos3, is(notNullValue()));
    }

    // タイトルに基づいてTodoを取得するテスト
    @Test
    void getTodoByTitle() {
        // "ap"を含むタイトルのTodoを取得
        List<Todo> matchAP = todoMapper.getTodoByTitle("ap");
        // "hoge"を含むタイトルのTodoを取得
        List<Todo> matchHoge = todoMapper.getTodoByTitle("hoge");

        // "ap"を含むTodoが2つあることを確認
        assertThat(matchAP, hasSize(2));
        // "hoge"を含むTodoの最初のタイトルが"hoge"であることを確認
        assertThat(matchHoge.get(0).getTitle(), equalTo("hoge"));
    }

    // IDに基づいてTodoを取得するテスト
    @Test
    void getTodoById() {
        // IDが1のTodoを取得
        Todo todo = todoMapper.getTodoById(1L);
        // 取得したTodoのタイトルが"タスク１"であることを確認
        assertThat(todo.getTitle(), equalTo("タスク１"));
    }

    // Todoを挿入するテスト
    @Test
    void insertTodo() {
        // テスト用のTodoオブジェクトをセットアップ
        Todo todo = setUpTodo();
        // Todoをマッパーを通じて挿入
        todoMapper.insertTodo(todo);

        System.out.println(todo.getId());

        // TodoのIDがnullでないことを確認
        assertThat(todo.getId(), is(notNullValue()));

        // 挿入後のTodoをIDを使用して再度取得
        Todo insertedTodo = todoMapper.getTodoById(todo.getId());

        // 取得したTodoがnullでないことを確認
        assertThat(insertedTodo, is(notNullValue()));
        // 取得したTodoのタイトルが"Test Todo"であることを確認
        assertThat(insertedTodo.getTitle(), is(equalTo("Test Todo")));
    }

    // Todoを更新するテスト
    @Test
    void updateTodo() {
        // テスト用のTodoオブジェクトをセット/"アップ
        Todo todo = setUpTodo();
        // Todoをマッパーを通じて挿入
        todoMapper.insertTodo(todo);



        // 更新前のTodoをIDを使用して再度取得
        Todo beforeUpdate = todoMapper.getTodoById(todo.getId());
        // 更新前のTodoのタイトルが"Test Todo"であることを確認
        assertThat(beforeUpdate.getTitle(), is(equalTo("Test Todo")));

        // Todoのタイトルを更新
        todo.setTitle("Updated Todo");
        // Todoをマッパーを通じて更新
        todoMapper.updateTodo(todo);

        // 更新後のTodoをIDを使用して再度取得
        Todo afterUpdate = todoMapper.getTodoById(todo.getId());
        // 更新後のTodoのタイトルが"Updated Todo"であることを確認
        assertThat(afterUpdate.getTitle(), is(equalTo("Updated Todo")));
    }

    // IDに基づいてTodoを削除するテスト
    @Test
    void deleteTodoById() {
        // テスト用のTodoオブジェクトをセットアップ
        Todo todo = setUpTodo();
        // Todoをマッパーを通じて挿入
        todoMapper.insertTodo(todo);

        // 削除前のTodoをIDを使用して再度取得
        Todo beforeDelete = todoMapper.getTodoById(todo.getId());
        // 削除前のTodoがnullでないことを確認
        assertThat(beforeDelete, is(notNullValue()));

        // TodoをIDを使用して削除
        todoMapper.deleteTodoById(todo.getId());

        // 削除後のTodoをIDを使用して再度取得
        Todo afterDelete = todoMapper.getTodoById(todo.getId());
        // 削除後のTodoがnullであることを確認
        assertThat(afterDelete, is(nullValue()));
    }

    @Disabled("未実装")
    @Test
    void getTodoSortedById() {
    }

    @Disabled("未実装")
    @Test
    void getTodoSortedByStatus() {
    }
}