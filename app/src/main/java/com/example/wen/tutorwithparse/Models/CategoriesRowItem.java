package com.example.wen.tutorwithparse.Models;

/**
 * Created by Benson on 4/15/2016.
 */
public class CategoriesRowItem {
    private int imageId;
    private String title;

    public CategoriesRowItem(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title + "\n";
    }
}
