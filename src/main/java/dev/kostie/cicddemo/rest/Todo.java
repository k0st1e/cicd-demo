package dev.kostie.cicddemo.rest;

public record Todo(Long id,
                   Long userId,
                   String title,
                   Boolean completed) {
}
