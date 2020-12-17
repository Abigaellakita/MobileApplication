package com.example.ebook_system.model;

public class BookContent {
    int ids;
    String chapters;
    String titles;
    String details;
    int book_id;

    public BookContent(int ids, String chapters, String titles, String details, int book_id) {
        this.ids = ids;
        this.chapters = chapters;
        this.titles = titles;
        this.details = details;
        this.book_id = book_id;
    }

    public BookContent(int ids, String chapters, String titles, String details) {
        this.ids = ids;
        this.chapters = chapters;
        this.titles = titles;
        this.details = details;
    }

    public BookContent(String chapters, String titles, String details, int book_id) {
        this.chapters = chapters;
        this.titles = titles;
        this.details = details;
        this.book_id = book_id;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getChapters() {
        return chapters;
    }

    public void setChapters(String chapters) {
        this.chapters = chapters;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
