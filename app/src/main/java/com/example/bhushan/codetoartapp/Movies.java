package com.example.bhushan.codetoartapp;

import java.io.Serializable;

/**
 * Created by bhushan on 23/1/18.
 */

public class Movies implements Serializable {
    String id,rdate,category,overview,image,title,rating;

    public Movies(String id, String rdate, String category, String overview, String image, String title, String rating) {
        this.id = id;
        this.rdate = rdate;
        this.category = category;
        this.overview = overview;
        this.image = image;
        this.title = title;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
