package ru.otus.homework3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework3.domain.Genre;
import ru.otus.homework3.service.GenreService;

import java.util.List;

@Controller
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("genre-operations/genres/add")
    public String createPage() {
        return "genreSave";
    }

    @PostMapping("/genre-operations/genres/add")
    public String create(@RequestParam(name = "genre") String genreName) {
        genreService.saveGenre(genreName);
        return "redirect:/genre-operations/genres";
    }

    @GetMapping("/genre-operations/genres/{name}")
    public String getGenreByName(@PathVariable String name, Model model) {
        model.addAttribute("genre", genreService.getGenreByName(name));
        return "genre";
    }

    @GetMapping("/genre-operations/genres")
    public String getAll(Model model) {
        final List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);
        return "genreList";
    }

    @GetMapping("/genre-operations/genres/edit/{oldGenreName}")
    public String editPage(@PathVariable String oldGenreName, Model model) {
        model.addAttribute("genre", genreService.getGenreByName(oldGenreName));
        return "genreEdit";
    }

    @PostMapping("/genre-operations/genres/edit/{oldGenreName}")
    public String update(@PathVariable String oldGenreName, @RequestParam(name = "genre") String genreName) {
        genreService.updateGenre(oldGenreName, genreName);
        return "redirect:/genre-operations/genres";
    }

    @PostMapping("/genre-operations/genres/{name}")
    public String deleteByName(@PathVariable String name) {
        genreService.deleteGenreByName(name);
        return "redirect:/genre-operations/genres";
    }
}
