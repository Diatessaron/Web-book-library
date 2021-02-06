package ru.otus.homework3.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework3.domain.Comment;
import ru.otus.homework3.rest.dto.CommentRequest;
import ru.otus.homework3.service.CommentService;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/comments")
    public ResponseEntity<String> save(@Validated CommentRequest commentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.saveComment
                (commentRequest.getBook(), commentRequest.getContent()));
    }

    @GetMapping("/api/comments/{comment}")
    public ResponseEntity<Comment> getCommentByContent(@PathVariable String comment) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentByContent(comment));
    }

    @GetMapping("/api/comments/book/{bookTitle}")
    public ResponseEntity<List<Comment>> getCommentByBookTitle(@PathVariable String bookTitle) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsByBook(bookTitle));
    }

    @GetMapping("/api/comments")
    public ResponseEntity<List<Comment>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAll());
    }

    @PutMapping("/api/comments/{oldComment}")
    public ResponseEntity<String> edit(@PathVariable String oldComment,
                                       @RequestParam("comment") String comment) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(oldComment, comment));
    }

    @DeleteMapping("/api/comments/{comment}")
    public ResponseEntity<String> deleteByContent(@PathVariable String comment) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.deleteByContent(comment));
    }
}
