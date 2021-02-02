package ru.otus.homework3.service;

import ru.otus.homework3.domain.Comment;

import java.util.List;

public interface CommentService {
    String saveComment(String bookTitle, String commentContent);

    Comment getCommentByContent(String content);

    List<Comment> getCommentsByBook(String bookTitle);

    List<Comment> getAll();

    String updateComment(String oldCommentContent, String commentContent);

    String deleteByContent(String content);
}
