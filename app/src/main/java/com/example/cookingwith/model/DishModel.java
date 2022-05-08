package com.example.cookingwith.model;

import java.util.Objects;

public class DishModel {

    private int photoId;
    private String title;
    private String description;
    private String dishUrl;

    public DishModel(String title, String description, int photoId, String dishUrl) {
        this.title = title;
        this.description = description;
        this.photoId = photoId;
        this.dishUrl = dishUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishModel dishModel = (DishModel) o;
        return photoId == dishModel.photoId &&
                Objects.equals(title, dishModel.title) &&
                Objects.equals(description, dishModel.description) &&
                Objects.equals(dishUrl, dishModel.dishUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoId, title, description, dishUrl);
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

    public String getDishUrl() {
        return dishUrl;
    }

    public void setDishUrl(String dishUrl) {
        this.dishUrl = dishUrl;
    }
}
