package ru.otus.homework3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework3.domain.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByName(String name);

    void deleteByName(String name);
}
