package com.restapi.react.reactapi.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.react.reactapi.todo.repository.TodoRepo;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class TodoJpaResource {

    private TodoRepo todoRepo;

    public TodoJpaResource(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @GetMapping(path = "users/{username}/todos")
    public List<Todo> retriveTodos(@PathVariable String username) {
        return todoRepo.findByUsername(username);
    }

    @GetMapping(path = "users/{username}/todos/{id}")
    public Todo retriveTodo(@PathVariable String username, @PathVariable int id) {
        return todoRepo.findById(id).get();
    }

    @DeleteMapping(path = "users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
        todoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username,
            @PathVariable int id, @RequestBody Todo todo) {
        todoRepo.save(todo);
        return todo;
    }

    @PostMapping(path = "users/{username}/todos")
    public Todo createTodo(@PathVariable String username,
            @RequestBody Todo todo) {
        todo.setUsername(username);
        todo.setId(null);
        return todoRepo.save(todo);
    }

}
