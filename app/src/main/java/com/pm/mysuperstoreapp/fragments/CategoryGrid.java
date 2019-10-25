package com.pm.mysuperstoreapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.adapters.CategoryGridAdapter;
import com.pm.mysuperstoreapp.models.ItemModel;

import java.util.List;

public class CategoryGrid extends Fragment {

    List<ItemModel> items;
    public static final String TAG = "mytag";
    private GridView gridView;
    private ImageButton iBAddItem;
    private ImageButton iBRemoveItem;
    private ImageButton iBAddToShoppingCart;
    private TextView tVItemQuantity;


    public CategoryGrid(List<ItemModel> itemList) {
        items = itemList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_category_grid, container, false);

        initViews(view);

        CategoryGridAdapter categoryGridAdapter = new CategoryGridAdapter(requireActivity());
        categoryGridAdapter.addItems(items);
        gridView.setAdapter(categoryGridAdapter);
        categoryGridAdapter.notifyDataSetChanged();

        return view;
    }

    private void initViews(View view) {
        gridView = view.findViewById(R.id.gVCategoryItems);
        iBAddItem = view.findViewById(R.id.grid_single_product_add_item);
        iBRemoveItem = view.findViewById(R.id.grid_single_product_remove_item);
        iBAddToShoppingCart = view.findViewById(R.id.grid_single_product_add_to_shopping_cart);
        tVItemQuantity = view.findViewById(R.id.grid_single_product_textview_item_quantity);

    }
}
