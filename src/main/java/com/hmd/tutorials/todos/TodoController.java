package com.hmd.tutorials.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/todos")
public class TodoController {

  @Autowired
  private TodoService todoService;

  @RequestMapping(value = {"", "/"})
  public ResponseEntity<List<Todo>> listTodos() {
    return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Todo> getTodo(@PathVariable String id){
    return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
    return new ResponseEntity<>(todoService.save(todo), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
    todoService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Todo> updateTodo(@Valid @RequestBody Todo newTodo, @PathVariable String id) {
    Todo oldTodo = todoService.getTodo(id);

    if (oldTodo != null) {
      oldTodo.setId(newTodo.getId());
      oldTodo.setTitle(newTodo.getTitle());
      oldTodo.setDescription(newTodo.getDescription());
      return new ResponseEntity<>(todoService.save(oldTodo), HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}
