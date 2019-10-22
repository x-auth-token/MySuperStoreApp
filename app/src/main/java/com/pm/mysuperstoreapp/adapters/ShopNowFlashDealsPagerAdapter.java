package com.pm.mysuperstoreapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.models.PictureViewModel;

import java.util.List;

public class ShopNowFlashDealsPagerAdapter extends PagerAdapter {
    Context context;
    //int images[];
    List<PictureViewModel> pictures;
    LayoutInflater layoutInflater;


    /*public ShopNowFlashDealsPagerAdapter(Context context, int images[]) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/

    public ShopNowFlashDealsPagerAdapter(Context context, List<PictureViewModel> discountImagesUrls) {
        this.context = context;
        this.pictures = discountImagesUrls;


        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //TODO: Get images from Firebase Storage!

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.fragment_shop_now_flash_deals, container, false);



        ImageView imageView = itemView.findViewById(R.id.iVFlashDealsPicture);




       //ImageView imageView = (ImageView) itemView.findViewById(R.id.iVHotDealPic);
        //imageView.setImageResource(discountImagesUrls.get(position));
        Glide.with(this.context).load(pictures.get(position).getUrl()).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(R.drawable.grain).into(imageView);
        container.addView(itemView);

        //listening to image click
        /*imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });*/

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}


