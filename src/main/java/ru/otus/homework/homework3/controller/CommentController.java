package ru.otus.homework.homework3.controller;

import org.springframework.stereotype.Controller;
import ru.otus.homework.homework3.service.CommentService;

//TODO: Finish
@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
}
