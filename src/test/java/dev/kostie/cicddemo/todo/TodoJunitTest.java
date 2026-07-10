package dev.kostie.cicddemo.todo;
import org.junit.jupiter.api.Test;
// ALT + ENTER -> Add static import
// This is done so we can have
// `assertEquals` rather than Assertions.{code}.
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoJunitTest {
    @Test
    void shouldCreateNewTodo () {
        var test = new Todo("Study", true);
        assertEquals("Study", test.name(), "TODO name was not equal to 'Study'");
        assertTrue(test.completed());
    }
}
