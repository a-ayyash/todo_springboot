package com.hmd.tutorials.todos;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TodoService {

  private List<Todo> data = new ArrayList<>(
          Arrays.asList(
                  new Todo(1, "first todo", "my first todo description"),
                  new Todo(2, "second todo", "my second todo description"),
                  new Todo(3, "third todo", "my third todo description"),
                  new Todo(4, "fourth todo", "my fourth todo description")
          )
  );

  public List<Todo> getAllTodos() {
    return data;
  }

  Todo getTodo(int id) {
    for (Todo todo : data) {
      if (todo.getId() == id)
        return todo;
    }

    return null;
  }

  boolean save(Todo newTodo) {
    for (Todo todo : data) {
      if (todo.getId() == newTodo.getId()) {
        data.remove(todo);
        return data.add(newTodo);
      }
    }

    return data.add(newTodo);
  }

  void delete(int id) {
    for (Todo todo : data) {
      if (todo.getId() == id) {
        data.remove(todo);
        break;
      }

    }

  }
}
