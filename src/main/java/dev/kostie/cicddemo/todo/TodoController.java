package dev.kostie.cicddemo.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/api/todos")
    ResponseEntity<List<Todo>> getAllTodos() {
        return new ResponseEntity<>(todoRepository.findAll(), HttpStatus.OK);
    }
}
