package ru.otus.homework3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework3.domain.Comment;
import ru.otus.homework3.dto.CommentRequest;
import ru.otus.homework3.service.CommentService;

import java.util.List;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment-operations/comments/add")
    public String savePage() {
        return "commentSave";
    }

    @PostMapping("/comment-operations/comments/add")
    public String save(@Validated CommentRequest commentRequest) {
        commentService.saveComment(commentRequest.getBook(), commentRequest.getContent());
        return "redirect:/comment-operations/comments";
    }

    @GetMapping("/comment-operations/comments/{content}")
    public String getCommentByContent(@PathVariable String content, Model model) {
        model.addAttribute("comment", commentService.getCommentByContent(content));
        return "comment";
    }

    @GetMapping("/comment-operations/comments/book/{bookTitle}")
    public String getCommentByBookTitle(@PathVariable String bookTitle, Model model) {
        model.addAttribute("comments", commentService.getCommentsByBook(bookTitle));
        return "commentList";
    }

    @GetMapping("/comment-operations/comments")
    public String getAll(Model model) {
        final List<Comment> comments = commentService.getAll();
        model.addAttribute("comments", comments);
        return "commentList";
    }

    @GetMapping("/comment-operations/comments/edit/{oldCommentContent}")
    public String editPage(@PathVariable String oldCommentContent, Model model) {
        model.addAttribute("comment", commentService.getCommentByContent(oldCommentContent));
        return "commentEdit";
    }

    @PostMapping("/comment-operations/comments/edit/{oldCommentContent}")
    public String edit(@PathVariable String oldCommentContent, @RequestParam("content") String content) {
        commentService.updateComment(oldCommentContent, content);
        return "redirect:/comment-operations/comments";
    }

    @PostMapping("/comment-operations/comments/{content}")
    public String deleteByContent(@PathVariable String content) {
        commentService.deleteByContent(content);
        return "redirect:/comment-operations/comments";
    }
}
