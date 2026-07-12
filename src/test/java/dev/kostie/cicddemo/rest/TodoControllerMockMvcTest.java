package dev.kostie.cicddemo.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// Bind to mock MVC.
// There is a sense of Spring Context here but not the whole of it.
// Think of it as a slice test.
@WebMvcTest(TodoRestController.class)
public class TodoControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    TodoService todoService;

    RestTestClient  restTestClient;

    @BeforeEach
    void setUp() {
        restTestClient = RestTestClient.bindTo(mockMvc).build();
    }

    @Test
    void findAllTodos() {
        when(todoService.findAll()).thenReturn(List.of(new Todo(
                1L, 1L, "Study", true))
        );

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
        assertEquals(true, todos.get(0).completed());
    }
}