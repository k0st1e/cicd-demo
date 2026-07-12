package dev.kostie.cicddemo.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// All the Spring Context, default W/O server.
// Integration testing.
@SpringBootTest
public class TodoControllerAppContextTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    // Real one! Not mocking.
    @Autowired
    private TodoService todoService;

    RestTestClient restTestClient;

    @BeforeEach
    public void setup(WebApplicationContext context) {

        // The client binds to Application Context.
        restTestClient = RestTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void findAllTodos() {

        List<Todo> todos = restTestClient.get()
                .uri("/api/todos/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Todo>>() {
                })
                .returnResult()
                .getResponseBody();

        // Otherwise method invocation ot `todos.size` throws warning of `NullPointerException`.
        assert todos != null;
        // Not the HTTP status of 200!! It is the size of the todos.
        assertEquals(200, todos.size());
    }
}
