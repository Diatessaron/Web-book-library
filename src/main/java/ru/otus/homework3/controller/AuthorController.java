package ru.otus.homework3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework3.domain.Author;
import ru.otus.homework3.service.AuthorService;

import java.util.List;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author-operations/authors/add")
    public String createPage() {
        return "authorSave";
    }

    @PostMapping("/author-operations/authors/add")
    public String create(@RequestParam(name = "author") String authorName) {
        authorService.saveAuthor(authorName);
        return "redirect:/author-operations/authors";
    }

    @GetMapping("/author-operations/authors/{name}")
    public String getAuthorByName(@PathVariable String name, Model model) {
        model.addAttribute("author", authorService.getAuthorByName(name));
        return "author";
    }

    @GetMapping("/author-operations/authors")
    public String getAll(Model model) {
        final List<Author> authors = authorService.getAll();
        model.addAttribute("authors", authors);
        return "authorList";
    }

    @GetMapping("/author-operations/authors/edit/{oldAuthorName}")
    public String editPage(@PathVariable String oldAuthorName, Model model) {
        model.addAttribute("author", authorService.getAuthorByName(oldAuthorName));
        return "authorEdit";
    }

    @PostMapping("/author-operations/authors/edit/{oldAuthorName}")
    public String edit(@PathVariable String oldAuthorName, @RequestParam(name = "author") String authorName) {
        authorService.updateAuthor(oldAuthorName, authorName);
        return "redirect:/author-operations/authors";
    }

    @PostMapping("/author-operations/authors/{name}")
    public String deleteByName(@PathVariable String name) {
        authorService.deleteAuthorByName(name);
        return "redirect:/author-operations/authors";
    }
}
