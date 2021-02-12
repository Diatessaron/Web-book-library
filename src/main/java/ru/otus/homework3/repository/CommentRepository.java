package ru.otus.homework3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework3.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByContent(String content);

    List<Comment> findByBook_Title(String bookTitle);

    void deleteByContent(String content);

    void deleteByBook_Title(String title);
}
