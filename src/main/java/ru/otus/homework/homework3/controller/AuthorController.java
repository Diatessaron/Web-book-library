package ru.otus.homework.homework3.controller;

import org.springframework.stereotype.Controller;
import ru.otus.homework.homework3.service.AuthorService;

//TODO: Finish
@Controller
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
}
