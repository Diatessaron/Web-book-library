package ru.otus.homework3.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework3.domain.Book;
import ru.otus.homework3.rest.dto.BookRequest;
import ru.otus.homework3.service.BookService;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/api/books")
    public ResponseEntity<Object> save(@Validated BookRequest bookRequest) {
        bookService.saveBook(bookRequest.getTitle(), bookRequest.getAuthorName(), bookRequest.getGenreName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/books/title/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookByTitle(title));
    }

    @GetMapping("/api/books")
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAll());
    }

    @PutMapping("/api/books/{oldBook}")
    public ResponseEntity<Object> edit(@PathVariable String oldBook, @Validated BookRequest bookRequest) {
        bookService.updateBook(oldBook, bookRequest.getTitle(), bookRequest.getAuthorName(), bookRequest.getGenreName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/api/books/{book}")
    public ResponseEntity<Object> deleteByTitle(@PathVariable String book) {
        bookService.deleteBookByTitle(book);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
