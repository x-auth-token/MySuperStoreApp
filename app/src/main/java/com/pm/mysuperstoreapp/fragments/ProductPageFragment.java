/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.adapters.ProductsPagerAdapter;
import com.pm.mysuperstoreapp.models.ProductViewModelToRemove;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class ProductPageFragment extends Fragment {

    private TextView mTvCategoryName;
    private ImageButton mBtnBack;
    private ViewPager mVpProducts;

    public final static String argCategoryName = "CategoryName";
    private List<ProductViewModelToRemove> productsList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_now_category, container, false);

        initViews(view);
        populateProducts();

        return view;
    }

    private void populateProducts() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String categoryName = bundle.getString(argCategoryName);
            if (categoryName != null) {
                mTvCategoryName.setText(categoryName);
                //TODO get products from firebase based on category name (better id)
                productsList = new ArrayList<>();
                productsList.add(new ProductViewModelToRemove(1, "url1", 50, "cucumber", "tasty"));
                productsList.add(new ProductViewModelToRemove(2, "url2", 30.2, "tomato", "green"));
                productsList.add(new ProductViewModelToRemove(3, "url3", 230.23, "potato", "kartoshka"));

                mVpProducts.setAdapter(new ProductsPagerAdapter(productsList, getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
            }
        }
    }

    private void initViews(View view) {
        mTvCategoryName = view.findViewById(R.id.tVCategoryName);
        mBtnBack = view.findViewById(R.id.iBBack);
        mVpProducts = view.findViewById(R.id.vPProducts);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopNowFragment.childFragmentManager.popBackStack();
            }
        });
    }
}
