package dare.todoApp.controller;

import dare.todoApp.domain.Todo;
import dare.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/todo")
    public List<Todo> todo(@RequestParam("date") String date) {
        System.out.println(date);
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        List<Todo> todoList = todoService.findByDate(localDate);
        return todoList;
    }
}
