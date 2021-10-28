package com.example.dto;

public class GameDTO {
    private String name;
    private String price;
    private String date;
    private String description;
    private String avatar;
    private String category;
    private String link;

    public GameDTO() {
    }

    public GameDTO(String name, String price, String date, String description, String avatar, String category, String link) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
        this.avatar = avatar;
        this.category = category;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
