package ru.otus.homework.homework3.dto;

import java.util.Objects;

public class CommentRequest {
    private String id;
    private String content;
    private String book;

    public CommentRequest() {
    }

    public CommentRequest(String content, String book) {
        this.content = content;
        this.book = book;
    }

    public CommentRequest(String id, String content, String book) {
        this.id = id;
        this.content = content;
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentRequest that = (CommentRequest) o;
        return Objects.equals(id, that.id) && content.equals(that.content) && book.equals(that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, book);
    }

    @Override
    public String toString() {
        return "CommentRequest{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", book='" + book + '\'' +
                '}';
    }
}
