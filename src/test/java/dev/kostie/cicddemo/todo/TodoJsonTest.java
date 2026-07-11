package dev.kostie.cicddemo.todo;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoJsonTest {
    @Test
    public void shouldCompareJson() throws JSONException {
        var fetchedJsonData = getJsonData();
        var expectedJsonData = """ 
            {
            // Note that the `name` and `completed` are "mirrored" in relation to `fetchedJsonData`.
            // This has the implication of `strict` working with both `true` & `false.
            // Contrary if the elements of `expectedJsonData` flip, the test passes only
            // when `strict` is set to `false`.
            "todos" : [
                        {
                            "name": "Test One",
                            "completed": true
                        },
                        {
                            "name": "Test Two",
                            "completed": true
                        }
                    ]
        }
        """;

        // When `Strict` is false (recommended), it forgives re-ordering data
        // and extending results as long as all the expected elements are there.
        // This makes tests less brittle -- (Taken from the Docs).
        JSONAssert.assertEquals(expectedJsonData, fetchedJsonData, true);

    }

    @Test
    void shouldCompareJsonPath() {
        var jsonData = """ 
            {
            "todos" : [
                        {
                            "name": "Test One",
                            "completed": true
                        },
                        {
                            "name": "Test Two",
                            "completed": true
                        }
                    ]
            }
        """;

        // In the `jsonPath` get the `todos` `.length`.
        Integer length = JsonPath.read(jsonData, "$.todos.length()");
        String name = JsonPath.read(jsonData, "$.todos.[0].name");

        // JUnit. Expected and Actual.
        assertEquals(2,length);
        assertEquals("Test One", name);
    }

    // Simulate some JSON data.
    private String getJsonData() {
        return """ 
            {
            "todos" : [
                        {
                            "completed": true,
                            "name": "Test One"
                        },
                        {
                            "completed": true,
                            "name": "Test Two"
                        }
                    ]
        }
        """;
    }

}