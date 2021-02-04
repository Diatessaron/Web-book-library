package ru.otus.homework3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework3.domain.Comment;
import ru.otus.homework3.dto.CommentRequest;
import ru.otus.homework3.service.BookService;
import ru.otus.homework3.service.CommentService;

import java.util.List;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final BookService bookService;

    public CommentController(CommentService commentService, BookService bookService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @GetMapping("/comments/add")
    public String savePage(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "commentSave";
    }

    @PostMapping("/comments/add")
    public String save(@Validated CommentRequest commentRequest) {
        commentService.saveComment(commentRequest.getBook(), commentRequest.getContent());
        return "redirect:/comments";
    }

    @GetMapping("/comments/{comment}")
    public String getCommentByContent(@PathVariable String comment, Model model) {
        model.addAttribute("comment", commentService.getCommentByContent(comment));
        return "comment";
    }

    @GetMapping("/comments/book/{bookTitle}")
    public String getCommentByBookTitle(@PathVariable String bookTitle, Model model) {
        model.addAttribute("comments", commentService.getCommentsByBook(bookTitle));
        return "commentListByBook";
    }

    @GetMapping("/comments")
    public String getAll(Model model) {
        final List<Comment> comments = commentService.getAll();
        model.addAttribute("comments", comments);
        return "commentList";
    }

    @GetMapping("/comments/edit/{oldComment}")
    public String editPage(@PathVariable String oldComment, Model model) {
        model.addAttribute("comment", commentService.getCommentByContent(oldComment));
        return "commentEdit";
    }

    @PostMapping("/comments/edit/{oldComment}")
    public String edit(@PathVariable String oldComment, @RequestParam("comment") String comment) {
        commentService.updateComment(oldComment, comment);
        return "redirect:/comments";
    }

    @PostMapping("/comments/{comment}")
    public String deleteByContent(@PathVariable String comment) {
        commentService.deleteByContent(comment);
        return "redirect:/comments";
    }
}
