package ru.otus.homework.homework3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.homework3.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    List<Author> findByName(String name);

    void deleteByName(String name);
}
