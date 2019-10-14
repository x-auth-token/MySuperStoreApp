package com.pm.mysuperstoreapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pm.mysuperstoreapp.fragments.ProductFragment;
import com.pm.mysuperstoreapp.models.ProductViewModel;

import java.util.List;

public class ProductsPagerAdapter extends FragmentPagerAdapter {

    private List<ProductViewModel> productsList;

    public ProductsPagerAdapter(List<ProductViewModel> productsList, FragmentManager childFragmentManager, int behavior) {
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
