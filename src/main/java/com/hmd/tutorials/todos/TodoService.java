package com.hmd.tutorials.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

  @Autowired
  private TodoRepository todoRepository;

  public List<Todo> getAllTodos() {
    return todoRepository.findAll();
  }

  Todo getTodo(String id) {
    return todoRepository.findById(id).get();
  }

  Todo save(Todo newTodo) {
    return todoRepository.save(newTodo);
  }

  void delete(String id) {
    todoRepository.deleteById(id);
  }
}
