package ru.otus.homework.homework3.service;

import ru.otus.homework.homework3.domain.Book;

import java.util.List;

public interface BookService {
    void saveBook(String title, String authorNameParameter, String genreNameParameter);

    Book getBookByTitle(String title);

    Book getBookByAuthor(String author);

    Book getBookByGenre(String genre);

    Book getBookByComment(String comment);

    List<Book> getAll();

    void updateBook(String oldBookTitle, String title, String authorNameParameter, String genreNameParameter);

    void deleteBookByTitle(String title);
}
