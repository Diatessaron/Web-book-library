package ru.otus.homework3.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework3.domain.Book;
import ru.otus.homework3.service.AuthorService;
import ru.otus.homework3.service.BookService;
import ru.otus.homework3.service.GenreService;

import java.util.List;

@Controller
public class BookPageController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookPageController(BookService bookService, AuthorService authorService, GenreService genreService) {
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
}
