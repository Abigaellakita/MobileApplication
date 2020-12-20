package com.example.ebook_system.helper;

import android.graphics.Bitmap;

public class Book {
    int book_id;
    String Title;
    String desc;
    String release_year;
    int number_of_pages;
    float price;
    String ISBN;
    float rating;
    int status;
    String created_at;
    String last_updated;
    Bitmap image;
    int cat_id;
    String category_name;
    int author_id;
    int language_id;
    String authorFName;
    String authorLName;
    String language;
    String book_url;

    public Book() {
    }
    public Book(String title, String desc, String release_year, int number_of_pages, float price, String ISBN, Bitmap image, int cat_id) {
        Title = title;
        this.desc = desc;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.ISBN = ISBN;
        this.image = image;
        this.cat_id = cat_id;
    }


    public Book(String title, String desc, String release_year, int number_of_pages, float price, String ISBN, Bitmap image) {
        Title = title;
        this.desc = desc;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.ISBN = ISBN;
        this.image = image;
        this.status = status;
    }


    public Book(int book_id, String title, String desc, String release_year, int number_of_pages, float price, String ISBN, float rating, int status, Bitmap image) {
        this.book_id = book_id;
        Title = title;
        this.desc = desc;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.ISBN = ISBN;
        this.rating = rating;
        this.status = status;
        this.image = image;
    }

    public Book(int book_id, String title, String desc, Bitmap image) {
        this.book_id = book_id;
        Title = title;
        this.desc = desc;
        this.image = image;
    }

    public Book(String title, String release_year, int number_of_pages, float price, Bitmap image, String category_name) {
        Title = title;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.image = image;
        this.category_name = category_name;
    }



    public Book(String title, String desc, String release_year, int number_of_pages, float price, String ISBN, Bitmap image, int cat_id, int author_id, int language_id) {
        Title = title;
        this.desc = desc;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.ISBN = ISBN;
        this.image = image;
        this.cat_id = cat_id;
        this.author_id = author_id;
        this.language_id = language_id;
    }

    public Book(int book_id,String title, String desc, String release_year, int number_of_pages, float price, String ISBN, int status, Bitmap image, int cat_id, int author_id, int language_id) {
        this.book_id = book_id;
        Title = title;
        this.desc = desc;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.ISBN = ISBN;
        this.status = status;
        this.image = image;
        this.cat_id = cat_id;
        this.author_id = author_id;
        this.language_id = language_id;
    }

    public Book(String title, String desc, String release_year, int number_of_pages, float price, String ISBN, Bitmap image, String category_name, String authorFName, String authorLName, String language) {
        Title = title;
        this.desc = desc;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.ISBN = ISBN;
        this.image = image;
        this.category_name = category_name;
        this.authorFName = authorFName;
        this.authorLName = authorLName;
        this.language = language;
    }

    public Book(String title, String desc, String release_year, int number_of_pages, float price, String ISBN, Bitmap image, String category_name, String authorFName, String language) {
        Title = title;
        this.desc = desc;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.ISBN = ISBN;
        this.image = image;
        this.category_name = category_name;
        this.authorFName = authorFName;
        this.language = language;
    }

    public Book(String title, String release_year, int number_of_pages, float price, Bitmap image, String category_name, String authorFName, String language) {
        Title = title;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.image = image;
        this.category_name = category_name;
        this.authorFName = authorFName;
        this.language = language;
    }

    public Book(String title, String desc, String release_year, int number_of_pages, float price, String ISBN, Bitmap image, int cat_id, int author_id, int language_id, String book_url) {
        Title = title;
        this.desc = desc;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.ISBN = ISBN;
        this.image = image;
        this.cat_id = cat_id;
        this.author_id = author_id;
        this.language_id = language_id;
        this.book_url = book_url;
    }

    public Book(int book_id, String title, String desc, String release_year, int number_of_pages, float price, String ISBN, int status, Bitmap image, int cat_id, int author_id, int language_id, String book_url) {
        this.book_id = book_id;
        Title = title;
        this.desc = desc;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.ISBN = ISBN;
        this.status = status;
        this.image = image;
        this.cat_id = cat_id;
        this.author_id = author_id;
        this.language_id = language_id;
        this.book_url = book_url;
    }

    public String getBook_url() {
        return book_url;
    }

    public void setBook_url(String book_url) {
        this.book_url = book_url;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public String getAuthorFName() {
        return authorFName;
    }

    public void setAuthorFName(String authorFName) {
        this.authorFName = authorFName;
    }

    public String getAuthorLName() {
        return authorLName;
    }

    public void setAuthorLName(String authorLName) {
        this.authorLName = authorLName;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    //setters
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public void setNumber_of_pages(int number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }
    //getters
    public int getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDesc() {
        return desc;
    }

    public String getRelease_year() {
        return release_year;
    }

    public int getNumber_of_pages() {
        return number_of_pages;
    }

    public float getPrice() {
        return price;
    }

    public String getISBN() {
        return ISBN;
    }

    public float getRating() {
        return rating;
    }

    public int getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getLast_updated() {
        return last_updated;
    }
}
