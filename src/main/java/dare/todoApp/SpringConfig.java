package dare.todoApp;


import dare.todoApp.domain.Todo;
import dare.todoApp.repository.TodoRepository;
import dare.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class SpringConfig {

    private final TodoRepository todoRepository; // 이렇게 해놓으면 스프링 데이터 jpa가 알아서 구현체를 만들어서 스프링 빈에 등록해줌

    @Autowired
    public SpringConfig(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Bean
    public TodoService todoService() {
        return new TodoService(todoRepository);
    }
}
