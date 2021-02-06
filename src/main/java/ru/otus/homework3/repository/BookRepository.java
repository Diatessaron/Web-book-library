package ru.otus.homework3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework3.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByTitle(String title);

    List<Book> findByAuthor_Name(String author);

    List<Book> findByGenre_Name(String genre);

    void deleteByTitle(String title);

    void deleteByAuthor_Name(String author);

    void deleteByGenre_Name(String genre);
}
