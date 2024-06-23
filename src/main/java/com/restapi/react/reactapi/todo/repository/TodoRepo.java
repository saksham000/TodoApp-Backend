package com.restapi.react.reactapi.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.react.reactapi.todo.Todo;

public interface TodoRepo extends JpaRepository<Todo,Integer> {
    public List<Todo> findByUsername(String username);
}
