package com.pm.mysuperstoreapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.models.PictureViewModel;

import java.util.List;

public class ShopNowFlashDealsViewFlipperAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<PictureViewModel> pictures;
    private Context context;

    public ShopNowFlashDealsViewFlipperAdapter(Context context, List<PictureViewModel> pictures) {

        this.pictures = pictures;
        this.context = context;

    }

    @Override
    public int getCount() {
        return (!pictures.isEmpty()) ? pictures.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        PictureViewModel picture = pictures.get(position);
        inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.fragment_shop_now_flash_deals, null);

        ImageView imageView = view.findViewById(R.id.iVFlashDealsPicture);

        Glide.with(this.context).load(pictures.get(position).getUrl()).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(R.drawable.grain).into(imageView);
        return view;
    }
}


