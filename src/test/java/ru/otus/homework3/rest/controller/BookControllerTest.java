package ru.otus.homework3.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework3.domain.Author;
import ru.otus.homework3.domain.Book;
import ru.otus.homework3.domain.Genre;
import ru.otus.homework3.service.BookServiceImpl;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceImpl bookService;

    @Test
    void testCreateByStatus() throws Exception {
        when(bookService.getAll()).thenReturn(List.of(new Book("Modernist novel",
                        new Author("James Joyce"), new Genre("Modernist novel")),
                new Book("Book", new Author("Author"), new Genre("Genre"))));

        mockMvc.perform(post("/api/books")
                .param("book", "Book"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetBookByTitleByStatus() throws Exception {
        when(bookService.getBookByTitle("Book")).thenReturn(new Book("Book", new Author("Author"),
                new Genre("Genre")));

        mockMvc.perform(get("/api/books/title/Book"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllByStatus() throws Exception {
        when(bookService.getAll()).thenReturn(List.of(new Book("Modernist novel",
                        new Author("James Joyce"), new Genre("Modernist novel")),
                new Book("Book", new Author("Author"), new Genre("Genre"))));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateByStatus() throws Exception {
        doNothing().when(bookService).updateBook
                ("Ulysses", "Book", "Author", "Genre");

        mockMvc.perform(put("/api/books/Ulysses")
                .param("title", "Book")
                .param("authorName", "Author")
                .param("genreName", "Genre"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteByStatus() throws Exception {
        doNothing().when(bookService).deleteBookByTitle("Ulysses");

        mockMvc.perform(delete("/api/books/Ulysses"))
                .andExpect(status().isOk());
    }
}
