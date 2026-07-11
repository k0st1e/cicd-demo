package dev.kostie.cicddemo.todo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TodoAssertjTest {
    @Test
    public void shouldCreateNewTodo() {
        var test = new Todo("COOK", false);
        assertThat(test.name())
                .startsWith("C")
                .endsWith("K")
                .contains("OO")
                .isEqualToIgnoringCase("cook");
    }
}
