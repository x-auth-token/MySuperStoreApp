package com.pm.mysuperstoreapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.signature.ObjectKey;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.models.ItemModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CategoryGridAdapter extends BaseAdapter {

    List<ItemModel> itemList;
    LayoutInflater layoutInflater;

    public CategoryGridAdapter(Context context) {

        itemList = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }


    public void addItems(List<ItemModel> itemList) {
        this.itemList = itemList;
    }

    public void clearItems() {
        this.itemList.clear();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("mytag", "getView: " + position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_single_product, parent, false);
            ImageButton iBtnCategoryButton = convertView.findViewById(R.id.iBGridSingleImage);
            TextView tvName = convertView.findViewById(R.id.tVGridSingleName);
            TextView tvPrice = convertView.findViewById(R.id.tVGridSinglePrice);

            Glide.with(convertView).load(itemList.get(position).getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(iBtnCategoryButton);
            tvName.setText(itemList.get(position).getName());
            tvPrice.setText(itemList.get(position).getPrice());
        }
        return convertView;
    }
}
