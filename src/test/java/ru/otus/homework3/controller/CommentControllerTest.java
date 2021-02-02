package ru.otus.homework3.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework3.domain.Comment;
import ru.otus.homework3.service.CommentServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentServiceImpl commentService;

    @Test
    void testSaveByStatus() throws Exception {
        when(commentService.getAll()).thenReturn(List.of
                (new Comment("Published in 1922", "Ulysses"),
                        new Comment("Comment", "Book")));

        mockMvc.perform(post("/comment-operations/comments/add")
                .param("comment", "Comment")
                .param("book", "Book"))
                .andExpect(status().isFound());
    }

    @Test
    void testGetCommentByContentByStatus() throws Exception {
        when(commentService.getCommentByContent("Comment")).thenReturn(new Comment("Comment", "Book"));

        mockMvc.perform(get("/comment-operations/comments/Comment"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCommentByBookTitleByStatus() throws Exception {
        when(commentService.getCommentsByBook("Book")).thenReturn(List.of
                (new Comment("Published in 1922", "Book"),
                        new Comment("Comment", "Book")));

        mockMvc.perform(get("/comment-operations/comments/book/Book"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllByStatus() throws Exception {
        when(commentService.getAll()).thenReturn(List.of
                (new Comment("Published in 1922", "Ulysses"),
                        new Comment("Comment", "Book")));

        mockMvc.perform(get("/comment-operations/comments"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateByStatus() throws Exception {
        when(commentService.updateComment("Comment", "Published in 1922"))
                .thenReturn("Comment was updated");

        mockMvc.perform(post("/comment-operations/comments/edit/Comment")
                .param("content", "Published in 1922"))
                .andExpect(status().isFound());
    }

    @Test
    void testDeleteByNameByStatus() throws Exception {
        when(commentService.deleteByContent("Comment")).thenReturn("Comment was deleted");

        mockMvc.perform(post("/comment-operations/comments/Comment"))
                .andExpect(status().isFound());
    }
}
