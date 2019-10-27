/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pm.mysuperstoreapp.fragments.ProductFragment;
import com.pm.mysuperstoreapp.models.ProductViewModelToRemove;

import java.util.List;

public class ProductsPagerAdapter extends FragmentPagerAdapter {

    private List<ProductViewModelToRemove> productsList;

    public ProductsPagerAdapter(List<ProductViewModelToRemove> productsList, FragmentManager childFragmentManager, int behavior) {
        super(childFragmentManager, behavior);
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new ProductFragment(productsList.get(position));
    }

    @Override
    public int getCount() {
        return productsList.size();
    }
}
