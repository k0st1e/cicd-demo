package dev.kostie.cicddemo.todo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
class TodoRepository {
    List<Todo> todos  = new ArrayList<>();

    public TodoRepository() {
        todos = List.of(new Todo("test one", false), new Todo("test two", false));
    }

    List<Todo> findAll() {
        return todos;
    }
}
