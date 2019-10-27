/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.models;

import androidx.lifecycle.ViewModel;
@Deprecated
public class ProductViewModelToRemove extends ViewModel {

    private int id;
    private String imageURL;
    private double price;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductViewModelToRemove(int id, String imageURL, double price, String name, String description) {
        this.id = id;
        this.imageURL = imageURL;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
