package test.hmd.tutorials.todos;

import com.hmd.tutorials.todos.Todo;
import com.hmd.tutorials.todos.TodoRepository;
import com.hmd.tutorials.todos.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RunWith(SpringRunner.class)
public class TodoServiceTest {

  @MockBean
  private TodoRepository todoRepository;

  @Autowired
  private TodoService todoService;

  @TestConfiguration
  static class TodoServiceContextConfiguration {
    @Bean
    public TodoService todoService() {
      return new TodoService();
    }
  }

  private List<Todo> todos;

  @Before
  public void setup() {
    Todo todo1 = new Todo("1", "Title Todo 1", "Desc Todo 1") ;
    Todo todo2 = new Todo("2", "Title Todo 2", "Desc Todo 2") ;
    Todo todo3 = new Todo("3", "Title Todo 3", "Desc Todo 3") ;
    Todo todo4 = new Todo("4", "Title Todo 4", "Desc Todo 4") ;

    todos = Arrays.asList(todo1, todo2, todo3, todo4);
  }

  @Test
  public void whenGETAll_returnsAll() {
    given(todoRepository.findAll()).willReturn(todos);
    assertThat(todoService.getAllTodos()).hasSize(todos.size()).contains(todos.get(new Random(System.currentTimeMillis()).nextInt(4)));
  }

  @Test
  public void whenGETByID_returnsTodo() {
    int ID = (new Random(System.currentTimeMillis())).nextInt(todos.size());
    given(todoRepository.findById(anyString())).willReturn(Optional.ofNullable(todos.get(ID)));
    Todo t = todoService.getTodo(Integer.toString(ID));

    assertThat(t.getTitle()).isEqualTo(todos.get(ID).getTitle());
  }


}
