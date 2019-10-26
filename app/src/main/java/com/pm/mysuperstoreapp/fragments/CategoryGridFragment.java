package com.pm.mysuperstoreapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.adapters.CategoryGridAdapter;
import com.pm.mysuperstoreapp.models.ProductViewModel;

import java.util.List;

public class CategoryGridFragment extends Fragment implements AdapterView.OnItemClickListener {

    List<ProductViewModel> items;
    public static final String TAG = "mytag";

    private GridView gridView;
    private CategoryGridAdapter categoryGridAdapter;


    public CategoryGridFragment(List<ProductViewModel> itemList) {
        items = itemList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_category_grid, container, false);

        initViews(view);

        categoryGridAdapter = new CategoryGridAdapter(requireActivity());
        categoryGridAdapter.addItems(items);
        gridView.setAdapter(categoryGridAdapter);
        categoryGridAdapter.notifyDataSetChanged();

        return view;
    }

    private void initViews(View view) {
        gridView = view.findViewById(R.id.gVCategoryItems);



    }



    /*@Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.grid_single_product_increase_quantity:
                System.out.println("TESTETSTET TETTST TETSt");
            *//*case categoryGridAdapterR.id.fragment_my_account_logout_button:
                //logout(view);
                break;
            case R.id.fragment_my_account_photo:
                //setProfilePicture(view);
                break;*//*

            default:
                break;
        }
    }*/

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
