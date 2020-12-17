package com.example.ebook_system.model;

import android.graphics.drawable.Drawable;

public class RecentlyViewed {
    String Title;
    String Description;
    String Language;
    String Price;
    Integer ImageUrl;
    //int BigImageUrl;

    public RecentlyViewed(String title, String description, String language, String price, Integer imageUrl) {
        Title = title;
        Description = description;
        Language = language;
        Price = price;
        ImageUrl = imageUrl;
        //BigImageUrl = bigImageUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public Integer getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        ImageUrl = imageUrl;
    }

    /*public int getBigImageUrl() {
        return BigImageUrl;
    }

    public void setBigImageUrl(int bigImageUrl) {
        BigImageUrl = bigImageUrl;
    }
    public Drawable getBigImageUrl() {
        return BigImageUrl;
    }
    public void setBigImageUrl(Drawable bigImageUrl) {
        BigImageUrl = bigImageUrl;
    }*/
}
