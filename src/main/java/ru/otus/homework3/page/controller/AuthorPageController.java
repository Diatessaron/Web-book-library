package ru.otus.homework3.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.homework3.service.AuthorService;

@Controller
public class AuthorPageController {
    private final AuthorService authorService;

    public AuthorPageController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors/add")
    public String createPage() {
        return "authorSave";
    }

    @GetMapping("/authors/{name}")
    public String getAuthorByName(@PathVariable String name, Model model) {
        model.addAttribute("author", authorService.getAuthorByName(name));
        return "author";
    }

    @GetMapping("/authors")
    public String getAll() {
        return "authorList";
    }

    @GetMapping("/authors/edit/{oldAuthor}")
    public String editPage(@PathVariable String oldAuthor, Model model) {
        model.addAttribute("author", authorService.getAuthorByName(oldAuthor));
        return "authorEdit";
    }
}
