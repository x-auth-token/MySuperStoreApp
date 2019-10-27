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
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.adapters.CategoryGridAdapter;
import com.pm.mysuperstoreapp.models.ProductViewModel;

import java.util.List;

// Grid Fragment to show products
public class CategoryGridFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<ProductViewModel> items;
    public static final String TAG = "mytag";

    private GridView gridView;


    public CategoryGridFragment(List<ProductViewModel> itemList) {
        items = itemList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_category_grid, container, false);

        initViews(view);

        // Using GridAdapter to populate cells
        CategoryGridAdapter categoryGridAdapter = new CategoryGridAdapter(requireActivity());
        categoryGridAdapter.addItems(items);
        gridView.setAdapter(categoryGridAdapter);
        categoryGridAdapter.notifyDataSetChanged();

        return view;
    }

    private void initViews(View view) {
        gridView = view.findViewById(R.id.gVCategoryItems);


    }


    // TODO: Implement this
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {

            case R.id.grid_single_product_increase_quantity:
                System.out.println("TESTETSTET TETTST TETSt");
                break;
            default:
                break;
        }
    }
}
