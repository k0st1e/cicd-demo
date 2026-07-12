package dev.kostie.cicddemo.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicTodoControllerTest {

    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        // Create an instance of the restTestClient, which binds to a Controller.
        restTestClient = RestTestClient.bindToController(new BasicTodoController()).build();
    }

    // Very fast. Does not load Spring Context whatsoever.
    // Unit testing -> functionality of `BasicTodoController`.
    @Test
    void findAll() {

        // Arrange the `todos`.
        List<Todo> todos = restTestClient.get()
                .uri("/api/todo/simple/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Todo>>() {})
                .returnResult()
                .getResponseBody();

        // Otherwise method invocation ot `todos.size` throws warning of `NullPointerException`.
        assert todos != null;
        assertEquals(1, todos.size());
        assertEquals("Study", todos.get(0).title());
    }

    @Test
    void findAllPath() {
        Todo todo = new Todo(1L, 1L, "Study", true);
        restTestClient.get()
                .uri("/api/todo/simple/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Todo.class)
                .isEqualTo(todo)
                // Redundant, learning purposes.
                .value(t -> {
                    assert t != null;
                    assertEquals(1L, t.id());
                    assertEquals(1L, t.userId());
                    assertEquals("Study", t.title());
                    assertEquals(true, t.completed());
                });
    }
}