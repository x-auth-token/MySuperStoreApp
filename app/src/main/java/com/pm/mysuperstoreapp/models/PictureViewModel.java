package com.pm.mysuperstoreapp.models;

import android.net.Uri;

import java.net.URL;

public class PictureViewModel {



    private String url;
    private String name;

    public PictureViewModel() {

    }

    public PictureViewModel( String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
