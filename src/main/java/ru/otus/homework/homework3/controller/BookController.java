package ru.otus.homework.homework3.controller;

import org.springframework.stereotype.Controller;
import ru.otus.homework.homework3.service.BookService;

//TODO: Finish
@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
}
