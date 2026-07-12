package dev.kostie.cicddemo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// `RestTestClient` Unit Tests.
// See `BasicTodoControllerTest.java`.
@RestController
@RequestMapping("/api/todo/simple/")
public class BasicTodoController {
    @GetMapping("/")
    public List<Todo> findAll () {
        return List.of(new Todo(1L, 1L, "Study", true));
    }

    @GetMapping("/{id}")
    public Todo findAll (Long id) {
        return new Todo(1L, 1L, "Study", true);
    }
}