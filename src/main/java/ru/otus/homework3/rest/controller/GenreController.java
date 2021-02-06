package ru.otus.homework3.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework3.domain.Genre;
import ru.otus.homework3.service.GenreService;

import java.util.List;

@RestController
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/api/genres")
    public ResponseEntity<String> create(@RequestParam(name = "genre") String genreName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.saveGenre(genreName));
    }

    @GetMapping("/api/genres/{genre}")
    public ResponseEntity<Genre> getGenreByName(@PathVariable String genre) {
        return ResponseEntity.status(HttpStatus.OK).body(genreService.getGenreByName(genre));
    }

    @GetMapping("/api/genres")
    public ResponseEntity<List<Genre>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(genreService.getAll());
    }

    @PutMapping("/api/genres/{oldGenre}")
    public ResponseEntity<String> edit(@PathVariable String oldGenre, @RequestParam(name = "genre") String genreName) {
        return ResponseEntity.status(HttpStatus.OK).body(genreService.updateGenre(oldGenre, genreName));
    }

    @DeleteMapping("/api/genres/{genre}")
    public ResponseEntity<String> deleteByName(@PathVariable String genre) {
        return ResponseEntity.status(HttpStatus.OK).body(genreService.deleteGenreByName(genre));
    }
}
