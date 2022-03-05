package dare.todoApp;

import dare.todoApp.domain.Todo;
import dare.todoApp.repository.TodoRepository;
import dare.todoApp.service.TodoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TodoServiceIntegrationTest {

    @Autowired
    TodoRepository todoRepository;
    @Autowired
    TodoService todoService;

    @Test
    @DisplayName("할일 추가")
//    @Commit
    void join() {
        Todo todo = new Todo();
        todo.setTitle("todo1");

        todoService.addTodo(todo);

        Todo findTodo = todoService.findOne(todo.getId()).get();

        assertThat(todo.getId()).isEqualTo(findTodo.getId());
    }

    @Test
    @DisplayName("모두 조회")
    void findAll() {
        Todo todo1 = new Todo();
        todo1.setTitle("todo1");
        todoService.addTodo(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("todo2");
        todoService.addTodo(todo2);

        List<Todo> todoList = todoService.findTodos();

        assertThat(todoList.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("날짜로 조회")
    void findByDate() {
        LocalDate date = LocalDate.now();

        Todo todo1 = new Todo();
        todo1.setTitle("todo1");
        todoService.addTodo(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("todo2");
        todoService.addTodo(todo2);

        List<Todo> todoList = todoService.findByDate(date);

        assertThat(todoList.size()).isEqualTo(2);

    }


}
