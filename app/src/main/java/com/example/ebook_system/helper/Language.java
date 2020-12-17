package com.example.ebook_system.helper;

public class Language {
    int language_id;
    String name;
    String created_at;
    String last_updated;

    public Language() {
    }

    public Language(int language_id, String name) {
        this.language_id = language_id;
        this.name = name;
    }

    public Language(String name) {
        this.name = name;
    }
   //setters
    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }
    //getters

    public int getLanguage_id() {
        return language_id;
    }

    public String getName() {
        return name;
    }
}
