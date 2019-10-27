/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.fragments.ProductPageFragment;
import com.pm.mysuperstoreapp.fragments.ShopNowFragment;

import java.util.List;

public class RecyclerViewCategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> categories;

    public RecyclerViewCategoriesAdapter(List<String> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflatedRowView = layoutInflater.inflate(R.layout.shop_now_category_row, parent, false);
        return new CategoryViewHolder(inflatedRowView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
        final String category = categories.get(position);
        categoryViewHolder.tvCategoryName.setText(category);
        //categoryViewHolder.iVCategoryPicture.setImageDrawable(getResources().getDrawable());

        categoryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ShopNowFragment.childFragmentManager.beginTransaction();

                ProductPageFragment productFragment = new ProductPageFragment();

                Bundle bundle = new Bundle();
                //TODO change to pass category id instead of string
                bundle.putString(ProductPageFragment.argCategoryName, category);

                productFragment.setArguments(bundle);

                transaction.replace(R.id.fragment_shop_now_main_frame, productFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    private class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView iVCategoryPicture;
        TextView tvCategoryName;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tVCategoryName);
            iVCategoryPicture = itemView.findViewById(R.id.iVCategoryPic);
        }
    }


}
