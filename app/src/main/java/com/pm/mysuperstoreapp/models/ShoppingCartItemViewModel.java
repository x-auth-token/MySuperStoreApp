/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.models;

// Represents item in shopping cart
public class ShoppingCartItemViewModel {

    private String productName;
    private String productImageUrl;
    private int quantity;
    private double subTotal;

    public ShoppingCartItemViewModel() {

    }

    public ShoppingCartItemViewModel(ProductViewModel product, int quantity) {
        this.productName = product.getName();
        this.productImageUrl = product.getImageUrl();
        this.quantity = quantity;
        setSubTotal(quantity, product);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int quantity, ProductViewModel product) {

        this.subTotal = quantity * Double.parseDouble(product.getPrice());
    }
}
