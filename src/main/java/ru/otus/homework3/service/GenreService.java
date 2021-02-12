package ru.otus.homework3.service;

import ru.otus.homework3.domain.Genre;

import java.util.List;

public interface GenreService {
    String saveGenre(String name);

    Genre getGenreById(String id);

    Genre getGenreByName(String name);

    List<Genre> getAll();

    String updateGenre(String id, String name);

    String deleteGenre(String id);
}
