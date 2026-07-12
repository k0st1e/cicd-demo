package dev.kostie.cicddemo.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// `RestTestClient` and mocking.
class TodoControllerMockTest {

    RestTestClient restTestClient;
    TodoService todoService;

    @BeforeEach
    void setUp() {

        // Mock-out `TodoService`.
        todoService = Mockito.mock(TodoService.class);
        Mockito.when(todoService.findAll()).thenReturn(
                List.of(new Todo(1L, 1L, "Study", true))
        );

        restTestClient = RestTestClient.bindToController(new TodoRestController(todoService)).build();
    }

    @Test
    void findAll() {
        List<Todo> todos = restTestClient.get()
                .uri("/api/todos/")
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
}