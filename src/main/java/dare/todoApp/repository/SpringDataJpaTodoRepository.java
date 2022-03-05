package dare.todoApp.repository;

import dare.todoApp.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SpringDataJpaTodoRepository extends JpaRepository<Todo, Long>, TodoRepository{

    @Override
    public List<Todo> findByDate(LocalDate date);
}
