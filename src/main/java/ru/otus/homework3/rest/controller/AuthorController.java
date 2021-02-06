package ru.otus.homework3.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework3.domain.Author;
import ru.otus.homework3.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/api/authors")
    public ResponseEntity<String> create(@RequestParam(name = "author") String authorName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.saveAuthor(authorName));
    }

    @GetMapping("/api/authors/{name}")
    public ResponseEntity<Author> getAuthorByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.getAuthorByName(name));
    }

    @GetMapping("/api/authors")
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.getAll());
    }

    @PutMapping("/api/authors/{oldAuthor}")
    public ResponseEntity<String> edit(@PathVariable String oldAuthor, @RequestParam(name = "author") String authorName) {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.updateAuthor(oldAuthor, authorName));
    }

    @DeleteMapping("/api/authors/{author}")
    public ResponseEntity<String> deleteByName(@PathVariable String author) {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.deleteAuthorByName(author));
    }
}
