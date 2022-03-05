package dare.todoApp.repository;

import dare.todoApp.domain.Todo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    Todo save(Todo todo);
    Optional<Todo> findById(Long id);
    List<Todo> findAll();
    List<Todo> findByDate(LocalDate date);
}
