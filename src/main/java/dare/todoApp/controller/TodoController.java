package dare.todoApp.controller;

import dare.todoApp.domain.Todo;
import dare.todoApp.form.TodoForm;
import dare.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ResponseBody
    @RequestMapping(path="/todo", method = RequestMethod.GET)
    public List<Todo> todo(@RequestParam("date") String date) {
        System.out.println(date);
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        List<Todo> todoList = todoService.findByDate(localDate);
        return todoList;
    }

    @PostMapping("/todo/add")
    public ResponseEntity addTodo(@RequestBody TodoForm todoForm) {
        Todo todo = new Todo();
        todo.setTitle(todoForm.getTitle());
        todo.setMemo(todoForm.getMemo());
        todo.setDate(LocalDate.parse(todoForm.getDate(), DateTimeFormatter.ISO_DATE));

        todoService.addTodo(todo);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
