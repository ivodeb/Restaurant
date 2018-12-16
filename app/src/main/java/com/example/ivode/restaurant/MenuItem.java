package com.example.ivode.restaurant;

import java.io.Serializable;

/** Serializable menu item with name, description, imageUrl, category and price. */
public class MenuItem implements Serializable {

    private String name, description, imageUrl, category, price;

    public MenuItem(String name, String description, String imageUrl, String category, String price) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    String getImageUrl() {
        return imageUrl;
    }
}