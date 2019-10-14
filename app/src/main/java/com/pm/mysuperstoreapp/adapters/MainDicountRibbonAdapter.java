package com.pm.mysuperstoreapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.pm.mysuperstoreapp.R;

import java.util.List;

public class MainDicountRibbonAdapter extends PagerAdapter {
    Context context;
    //int images[];
    List<String> discountImagesUrls;
    LayoutInflater layoutInflater;


    /*public MainDicountRibbonAdapter(Context context, int images[]) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/

    public MainDicountRibbonAdapter(Context context, List<String> discountImagesUrls) {
        this.context = context;
        this.discountImagesUrls = discountImagesUrls;


        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return discountImagesUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.fragment_shop_now_with_dicount_ribbon, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.avtivity_main_dicount_ribbon_image_view);
        //imageView.setImageResource(discountImagesUrls.get(position));

        container.addView(itemView);

        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}


