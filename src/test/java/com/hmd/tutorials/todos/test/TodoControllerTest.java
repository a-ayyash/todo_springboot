package com.hmd.tutorials.todos.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmd.tutorials.todos.Todo;
import com.hmd.tutorials.todos.TodoController;
import com.hmd.tutorials.todos.TodoService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TodoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TodoService todoService;

  @Test
  public void whenGetAll_allIsReturned() throws Exception {
    Todo todo1 = new Todo("1", "Title Todo 1", "Desc Todo 1") ;
    Todo todo2 = new Todo("2", "Title Todo 2", "Desc Todo 2") ;
    Todo todo3 = new Todo("3", "Title Todo 3", "Desc Todo 3") ;
    Todo todo4 = new Todo("4", "Title Todo 4", "Desc Todo 4") ;

    List<Todo> todos = Arrays.asList(todo1, todo2, todo3, todo4);
    given(todoService.getAllTodos()).willReturn(todos);

    mockMvc.perform(get("/api/v1/todos").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)))
            .andExpect(jsonPath("$[0].title", equalTo(todo1.getTitle())));
  }

  @Test
  public void whenPost_todoIsCreated() throws Exception {
    Todo todo1 = new Todo("1", "Title Todo 1", "Desc Todo 1") ;

    given(todoService.save(ArgumentMatchers.any(Todo.class))).willReturn(todo1);

    ObjectMapper mapper = new ObjectMapper();

    mockMvc.perform(
            post("/api/v1/todos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(todo1)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title", is("Title Todo 1")));

  }
}
