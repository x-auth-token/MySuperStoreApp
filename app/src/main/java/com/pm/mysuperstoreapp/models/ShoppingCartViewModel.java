/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.models;

import android.util.Log;

import androidx.lifecycle.ViewModel;


import java.util.HashMap;

// Represents shopping cart
public class ShoppingCartViewModel extends ViewModel {


    private HashMap<String, ShoppingCartItemViewModel> itemsOnCart = new HashMap<String, ShoppingCartItemViewModel>();
    double total = 0;



    public ShoppingCartViewModel() {

    }

    public void addItemToCart(ShoppingCartItemViewModel cartItem) {
        itemsOnCart.put(cartItem.getProductName(), cartItem);
    }

    public void removeItemFromCart(ShoppingCartItemViewModel cartItem) {
        if (!itemsOnCart.isEmpty() && itemsOnCart.containsKey(cartItem.getProductName())) {
            itemsOnCart.remove(cartItem.getProductName());
        } else {
            Log.d("ShoppingCartRemoveItem", "Item not found");
        }

    }

    public double calculateTotal() {
        for (HashMap.Entry<String, ShoppingCartItemViewModel> item: itemsOnCart.entrySet()) {
            total += item.getValue().getSubTotal();
        }

        return total;
    }


}
