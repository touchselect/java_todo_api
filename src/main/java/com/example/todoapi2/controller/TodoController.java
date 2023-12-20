package com.example.todoapi2.controller;

import com.example.todoapi2.model.Status;
import com.example.todoapi2.model.Todo;
import com.example.todoapi2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping({"/sort-id", "/sort-id/asc"})
    public ResponseEntity<List<Todo>> getTodoSortedById(){
        List<Todo> todos = todoService.getTodoSortedById();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/sort-id/desc")
    public ResponseEntity<List<Todo>> getTodoSortedByIdDesc(){
        List<Todo> todos = todoService.getTodoSortedByIdDesc();
        return ResponseEntity.ok(todos);
    }

    @GetMapping({"/sort-status", "/sort-status/asc"})
    public ResponseEntity<List<Todo>> getTodoSortedByStatus(){
        List<Todo> todos = todoService.getTodoSortedByStatus();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/sort-status/desc")
    public ResponseEntity<List<Todo>> getTodoSortedByStatusDesc(){
        List<Todo> todos = todoService.getTodoSortedByStatusDesc();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        todoService.createTodo(todo);
        return ResponseEntity.created(URI.create("/todos/" + todo.getId())).body(todo);
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
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo){
        todo.setId(id);
        boolean updated = todoService.updateTodo(todo);
        if(updated){
            return ResponseEntity.ok(todo);
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
