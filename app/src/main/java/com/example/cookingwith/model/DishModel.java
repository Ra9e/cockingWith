package com.example.cookingwith.model;

public class DishModel {

    private int photoId;
    private String title;
    private String description;

    public DishModel(String title, String description, int photoId) {
        this.title = title;
        this.description = description;
        this.photoId = photoId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
