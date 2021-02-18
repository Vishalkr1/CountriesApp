package com.example.countriesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("photos")
    @Expose
    private PhotosList photos;
    @SerializedName("stat")
    @Expose
    private String stat;

    public PhotosList getPhotos() {
        return photos;
    }

    public void setPhotos(PhotosList photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
