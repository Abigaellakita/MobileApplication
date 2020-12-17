package com.example.ebook_system.helper;

public class Category {
    int cat_id;
    String name;
    String created_at;
    String last_updated;

    public Category() {
    }

    public Category(int cat_id, String name) {
        this.cat_id = cat_id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }
    //setters

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
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

    public int getCat_id() {
        return cat_id;
    }

    public String getName() {
        return name;
    }
}
