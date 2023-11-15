package com.example.todoapi2.controller;

import com.example.todoapi2.model.Status;
import com.example.todoapi2.model.Todo;
import com.example.todoapi2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(){
        List<Todo> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Todo>> getTodoByStatus(@RequestParam Status status){
        List<Todo> todos = todoService.getTodoByStatus(status);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/title")
    public ResponseEntity<List<Todo>> getTodoByTitle(@RequestParam String title){
        List<Todo> todos = todoService.getTodoByTitle(title);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/sort-id")
    public ResponseEntity<List<Todo>> getTodoSortedById(@RequestParam String order){
        List<Todo> todos = todoService.getTodoSortedById(order);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/sort-status")
    public ResponseEntity<List<Todo>> getTodoSortedByStatus(@RequestParam String order){
        List<Todo> todos = todoService.getTodoSortedByStatus(order);
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<Void> createTodo(@RequestBody Todo todo){
        todoService.createTodo(todo);
        return ResponseEntity.created(URI.create("/todos/" + todo.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
        Todo todo = todoService.getTodoById(id);
        if(todo != null){
            return ResponseEntity.ok(todo);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable Long id, @RequestBody Todo todo){
        todo.setId(id);
        boolean updated = todoService.updateTodo(todo);
        if(updated){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        boolean deleted = todoService.deleteTodoById(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
