package ru.otus.homework3.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework3.domain.Genre;
import ru.otus.homework3.service.GenreService;

import java.util.List;

@Controller
public class GenrePageController {
    private final GenreService genreService;

    public GenrePageController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres/add")
    public String createPage() {
        return "genreSave";
    }

    @GetMapping("/genres/{genre}")
    public String getGenreByName(@PathVariable String genre, Model model) {
        model.addAttribute("genre", genreService.getGenreByName(genre));
        return "genre";
    }

    @GetMapping("/genres")
    public String getAll(Model model) {
        final List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);
        return "genreList";
    }

    @GetMapping("/genres/edit/{oldGenre}")
    public String editPage(@PathVariable String oldGenre, Model model) {
        model.addAttribute("genre", genreService.getGenreByName(oldGenre));
        return "genreEdit";
    }
}
