package dev.kostie.cicddemo.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerServerTest {

    @LocalServerPort
    private int port;

    private RestTestClient restTestClient;

    @BeforeEach
    public void setup() {
        restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .build();
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
        assertEquals(200, todos.size());
        assertEquals("voluptas quo tenetur perspiciatis explicabo natus", todos.get(24).title());

    }

    @Test
    void serverIsRunning() {
        assertTrue(port > 0, "Port should be greater than 0.");
        assertTrue(port != 8080, "Port should not be 8080.");
    }
}
