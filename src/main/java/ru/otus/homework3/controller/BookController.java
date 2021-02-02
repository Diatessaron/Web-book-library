package ru.otus.homework3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework3.domain.Book;
import ru.otus.homework3.dto.BookRequest;
import ru.otus.homework3.service.BookService;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book-operations/books/add")
    public String savePage(){
        return "bookSave";
    }

    @PostMapping("/book-operations/books/add")
    public String save(@Validated BookRequest bookRequest) {
        bookService.saveBook(bookRequest.getTitle(), bookRequest.getAuthorName(), bookRequest.getGenreName());
        return "redirect:/book-operations/books";
    }

    @GetMapping("/book-operations/books/title/{title}")
    public String getBookByTitle(@PathVariable String title, Model model) {
        model.addAttribute("book", bookService.getBookByTitle(title));
        return "book";
    }

    @GetMapping("/book-operations/books/author/{author}")
    public String getBookByAuthor(@PathVariable String author, Model model) {
        model.addAttribute("book", bookService.getBookByAuthor(author));
        return "book";
    }

    @GetMapping("/book-operations/books/genre/{genre}")
    public String getBookByGenre(@PathVariable String genre, Model model) {
        model.addAttribute("book", bookService.getBookByGenre(genre));
        return "book";
    }

    @GetMapping("/book-operations/books/comment")
    public String getBookByComment(@RequestParam("comment") String comment, Model model) {
        model.addAttribute("book", bookService.getBookByComment(comment));
        return "book";
    }

    @GetMapping("/book-operations/books")
    public String getAll(Model model) {
        final List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/book-operations/books/edit/{oldBookTitle}")
    public String editPage(@PathVariable String oldBookTitle, Model model){
        model.addAttribute("book", bookService.getBookByTitle(oldBookTitle));
        return "bookEdit";
    }

    @PostMapping("/book-operations/books/edit/{oldBookTitle}")
    public String edit(@PathVariable String oldBookTitle, @Validated BookRequest bookRequest) {
        bookService.updateBook(oldBookTitle, bookRequest.getTitle(), bookRequest.getAuthorName(), bookRequest.getGenreName());
        return "redirect:/book-operations/books";
    }

    @PostMapping("/book-operations/books/{title}")
    public String deleteByTitle(@PathVariable String title) {
        bookService.deleteBookByTitle(title);
        return "redirect:/book-operations/books";
    }
}
