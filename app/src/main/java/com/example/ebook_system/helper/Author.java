package com.example.ebook_system.helper;

public class Author {
    int author_id;
    String first_name;
    String last_name;
    String created_at;
    String last_updated;

    public Author() {
    }

    public Author(int author_id, String first_name, String last_name) {
        this.author_id = author_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Author(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }
    //setters

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }
    //getters

    public int getAuthor_id() {
        return author_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
}
