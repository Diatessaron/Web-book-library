package ru.otus.homework3.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.homework3.domain.Author;
import ru.otus.homework3.service.AuthorServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorServiceImpl authorService;

    @Test
    void testCreateByStatus() throws Exception {
        when(authorService.getAll()).thenReturn(List.of(new Author("James Joyce"),
                new Author("Author")));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/authors")
                .param("author", "Author"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAuthorByNameByStatus() throws Exception {
        when(authorService.getAuthorByName("Author")).thenReturn(new Author("Author"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/Author"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllByStatus() throws Exception {
        when(authorService.getAll()).thenReturn(List.of(new Author("James Joyce"),
                new Author("Author")));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors"))
                .andExpect(status().isOk());
    }

    @Test
    void testEditByStatus() throws Exception {
        when(authorService.updateAuthor("James Joyce", "Author"))
                .thenReturn("Author was updated");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/authors/James Joyce")
                .param("author", "Author"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteByNameByStatus() throws Exception {
        when(authorService.deleteAuthorByName("Author")).thenReturn("Author was deleted");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/authors/Author"))
                .andExpect(status().isOk());
    }
}
