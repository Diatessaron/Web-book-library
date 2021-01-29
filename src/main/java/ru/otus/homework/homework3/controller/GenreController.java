package ru.otus.homework.homework3.controller;

import org.springframework.stereotype.Controller;
import ru.otus.homework.homework3.service.GenreService;

//TODO: Finish
@Controller
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
}
