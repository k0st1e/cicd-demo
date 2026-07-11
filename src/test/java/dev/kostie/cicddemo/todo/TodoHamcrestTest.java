package dev.kostie.cicddemo.todo;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TodoHamcrestTest {
    @Test
    public void shouldCreateNewTodo() {
        var testOne = new Todo("Test", false);
        var testTwo = new Todo("Test", false);

        assertThat(testOne, equalTo(testTwo));
    }
}
