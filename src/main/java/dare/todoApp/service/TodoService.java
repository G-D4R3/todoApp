package dare.todoApp.service;

import dare.todoApp.domain.Todo;
import dare.todoApp.repository.TodoRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Long addTodo(Todo todo) {
        todoRepository.save(todo);
        return todo.getId();
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> findOne(Long Id) {
        return todoRepository.findById(Id);
    }

    public List<Todo> findByDate(LocalDate date) {
        return todoRepository.findByDate(date);
    }
}
