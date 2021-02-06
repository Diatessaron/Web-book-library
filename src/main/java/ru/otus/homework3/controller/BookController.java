package ru.otus.homework3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework3.domain.Book;
import ru.otus.homework3.dto.BookRequest;
import ru.otus.homework3.service.AuthorService;
import ru.otus.homework3.service.BookService;
import ru.otus.homework3.service.GenreService;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/books/add")
    public String savePage(Model model){
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        return "bookSave";
    }

    @PostMapping("/books/add")
    public String save(@Validated BookRequest bookRequest) {
        bookService.saveBook(bookRequest.getTitle(), bookRequest.getAuthorName(), bookRequest.getGenreName());
        return "redirect:/books";
    }

    @GetMapping("/books/title/{title}")
    public String getBookByTitle(@PathVariable String title, Model model) {
        model.addAttribute("book", bookService.getBookByTitle(title));
        return "bookByTitle";
    }

    @GetMapping("/books/author/{author}")
    public String getBookByAuthor(@PathVariable String author, Model model) {
        model.addAttribute("book", bookService.getBookByAuthor(author));
        return "bookByAuthor";
    }

    @GetMapping("/books/genre/{genre}")
    public String getBookByGenre(@PathVariable String genre, Model model) {
        model.addAttribute("book", bookService.getBookByGenre(genre));
        return "bookByGenre";
    }

    @GetMapping("/books/comment")
    public String getBookByComment(@RequestParam("comment") String comment, Model model) {
        model.addAttribute("book", bookService.getBookByComment(comment));
        return "bookByComment";
    }

    @GetMapping("/books")
    public String getAll(Model model) {
        final List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/books/edit/{oldBook}")
    public String editPage(@PathVariable String oldBook, Model model){
        model.addAttribute("book", bookService.getBookByTitle(oldBook));
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        return "bookEdit";
    }

    @PostMapping("/books/edit/{oldBook}")
    public String edit(@PathVariable String oldBook, @Validated BookRequest bookRequest) {
        bookService.updateBook(oldBook, bookRequest.getTitle(), bookRequest.getAuthorName(), bookRequest.getGenreName());
        return "redirect:/books";
    }

    @PostMapping("/books/{book}")
    public String deleteByTitle(@PathVariable String book) {
        bookService.deleteBookByTitle(book);
        return "redirect:/books";
    }
}
