package com.hmd.tutorials.todos;

import com.hmd.tutorials.error.TodoConflictException;
import com.hmd.tutorials.error.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

  @Autowired
  private TodoRepository todoRepository;

  public List<Todo> getAllTodos() {
    return todoRepository.findAll();
  }

  Todo getTodo(String id) {
    try {
      return todoRepository.findById(id).get();
    } catch (NoSuchElementException ex) {
      throw new TodoNotFoundException(ex.getMessage());
    }
  }

  Todo save(Todo newTodo) {
    if (todoRepository.findByTitle(newTodo.getTitle()) != null) {
      throw new TodoConflictException("Todo is already there!!!");
    }
    return todoRepository.save(newTodo);
  }

  void delete(String id) {
    todoRepository.deleteById(id);
  }
}
