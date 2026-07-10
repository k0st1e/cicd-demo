package dev.kostie.cicddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Dummy controller.
@Controller
public class CatController {
    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/page-one")
    public String pageOne() {
        return "page-one";
    }

    @GetMapping("/page-two")
    public String pageTwo() {
        return "page-two";
    }
}
