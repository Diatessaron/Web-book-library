package ru.otus.homework3.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework3.domain.Author;
import ru.otus.homework3.domain.Book;
import ru.otus.homework3.repository.AuthorRepository;
import ru.otus.homework3.repository.BookRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public String saveAuthor(String name) {
        final Author author = new Author(name);
        authorRepository.save(author);
        return String.format("You successfully saved a %s to repository", author.getName());
    }

    @Transactional(readOnly = true)
    @Override
    public Author getAuthorByName(String name) {
        return getAuthor(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional
    @Override
    public String updateAuthor(String oldAuthorName, String name) {
        final Author author = getAuthor(oldAuthorName);
        author.setName(name);
        authorRepository.save(author);

        final List<Book> bookList = bookRepository.findByAuthor_Name(oldAuthorName);

        if (!bookList.isEmpty()) {
            bookList.forEach(b -> b.setAuthor(author));
            bookRepository.saveAll(bookList);
        }

        return String.format("%s was updated", name);
    }

    @Transactional
    @Override
    public String deleteAuthorByName(String name) {
        final Author author = getAuthor(name);

        authorRepository.deleteByName(name);
        bookRepository.deleteByAuthor_Name(name);

        return String.format("%s was deleted", author.getName());
    }

    private Author getAuthor(String name){
        final List<Author> authors = authorRepository.findByName(name);

        if (authors.isEmpty())
            throw new IllegalArgumentException("Incorrect author name. Please, specify correct argument.");
        else if (authors.size() > 1)
            throw new IllegalArgumentException("Non unique result.");

        return authors.get(0);
    }
}
