/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.models.PictureViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

// Represent TOP banner in ShopNowFragment
public class ShopNowFlashDealsPagerAdapter extends PagerAdapter {
    private Context context;
    private List<PictureViewModel> pictures;
    private LayoutInflater layoutInflater;


    public ShopNowFlashDealsPagerAdapter(Context context, List<PictureViewModel> discountImagesUrls) {
        this.context = context;
        this.pictures = discountImagesUrls;


        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, Object object) {
        return view == object;
    }

    // Puts image from Firebase Storage into banner
    @NotNull
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.fragment_shop_now_flash_deals, container, false);


        ImageView imageView = itemView.findViewById(R.id.iVFlashDealsPicture);


        Glide.with(this.context).load(pictures.get(position).getUrl()).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(R.drawable.grain).into(imageView);
        container.addView(itemView);


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NotNull Object object) {
        container.removeView((LinearLayout) object);
    }
}


