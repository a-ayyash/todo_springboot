package com.hmd.tutorials.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/todos")
public class TodoController {

  @Autowired
  private TodoService todoService;

  @RequestMapping(value = {"", "/"})
  public List<Todo> listTodos() {
    return todoService.getAllTodos();
  }

  @GetMapping("/{id}")
  public Todo getTodo(@PathVariable int id){
    return todoService.getTodo(id);
  }

  @PostMapping(value = {"", "/"})
  public boolean createTodo(@RequestBody Todo todo) {
    return todoService.save(todo);
  }

  @DeleteMapping("/{id}")
  public void deleteTodo(@PathVariable int id) {
    todoService.delete(id);
  }

  @PutMapping(value = "/{id}")
  public Todo updateTodo(@RequestBody Todo newTodo, @PathVariable int id) {
    Todo oldTodo = todoService.getTodo(id);

    if (oldTodo != null) {
      oldTodo.setId(newTodo.getId());
      oldTodo.setTitle(newTodo.getTitle());
      oldTodo.setDescription(newTodo.getDescription());

      if (todoService.save(oldTodo)) {
        return oldTodo;
      }
    }

    return null;
  }
}
