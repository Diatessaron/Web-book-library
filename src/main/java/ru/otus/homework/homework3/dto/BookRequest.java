package ru.otus.homework.homework3.dto;

import java.util.Objects;

public class BookRequest {
    private String id;
    private String title;
    private String authorName;
    private String genreName;

    public BookRequest() {
    }

    public BookRequest(String title, String authorName, String genreName) {
        this.title = title;
        this.authorName = authorName;
        this.genreName = genreName;
    }

    public BookRequest(String id, String title, String authorName, String genreName) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.genreName = genreName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRequest that = (BookRequest) o;
        return Objects.equals(id, that.id) && title.equals(that.title) && authorName.equals(that.authorName) && genreName.equals(that.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authorName, genreName);
    }

    @Override
    public String toString() {
        return "BookRequest{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
