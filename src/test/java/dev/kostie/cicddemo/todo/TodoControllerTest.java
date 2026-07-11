package dev.kostie.cicddemo.todo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @Test loads the whole app (App Context)
// Contrary, we can do a "slice test" :D. A small slice of pie.
// Look into `TodoController` and `TodoRepository`.

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    // Mock-out the Repository otherwise this won't work. Look at comments below of (* and **).
    @MockitoBean
    TodoRepository todoRepository;

    // Testing the input and outputs of the web. Not the integration of the Database.
    // That's why we are mocking the repository with a Mockito Bean.
    @Test
    void shouldFindAllTodos() throws Exception {

        // *
        var todos = List.of(new Todo("test one", false), new Todo("test two", false));

        // ** When the `todoRepository` is called, don't call it, rather return something else.
        Mockito.when(todoRepository.findAll()).thenReturn(todos);

        MvcResult mvcResult = mockMvc.perform(get("/api/todos")).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        // Similarly, with a fluent API:
        mockMvc.perform(get("/api/todos"))
                .andExpectAll(
                        status().isOk()
                        // Add more here...
                );
    }
}