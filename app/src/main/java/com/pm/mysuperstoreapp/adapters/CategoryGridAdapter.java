/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.models.ProductViewModel;

import java.util.ArrayList;
import java.util.List;


// Grid adapter for showing products
public class CategoryGridAdapter extends BaseAdapter  {

    private List<ProductViewModel> itemList;
    private LayoutInflater layoutInflater;
    private ImageButton iBIncreaseQuantity;
    private TextView tVItemQuantity;
    private static final String DEFAULT_QUANTITY = "0";
    private String updatedQuantity = "0";

    public CategoryGridAdapter(Context context) {

        itemList = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    // Adds items to the list
    public void addItems(List<ProductViewModel> itemList) {
        this.itemList = itemList;
    }

    /*public void clearItems() {
        this.itemList.clear();
    }*/

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
    public View getView(int position, View convertView, final ViewGroup parent) {
        Log.d("mytag", "getView: " + position);

        View convertView1 = convertView;
        final int pos = position;

        if (convertView1 == null) {
            convertView1 = layoutInflater.inflate(R.layout.grid_single_product, parent, false);
            //ImageButton iBtnCategoryButton = this.convertView.findViewById(R.id.iBGridSingleImage);
            ImageView iVProductImage = convertView1.findViewById(R.id.iBGridSingleImage);
            TextView tvName = convertView1.findViewById(R.id.tVGridSingleName);
            TextView tvPrice = convertView1.findViewById(R.id.tVGridSinglePrice);

            iBIncreaseQuantity = convertView1.findViewById(R.id.grid_single_product_increase_quantity);
            ImageButton iBDecreaseQuantity = convertView1.findViewById(R.id.grid_single_product_decrease_quantity);
            ImageButton iBAddToShoppingCart = convertView1.findViewById(R.id.grid_single_product_add_to_shopping_cart);
            tVItemQuantity = convertView1.findViewById(R.id.grid_single_product_textview_item_quantity);


            Glide.with(convertView1).load(itemList.get(position).getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(iVProductImage);
            tvName.setText(itemList.get(position).getName());
            tvPrice.setText(itemList.get(position).getPrice());
        }

        if (tVItemQuantity.getText().toString().isEmpty()) {
            tVItemQuantity.setText(DEFAULT_QUANTITY);

            System.out.println("EMPTY TEST EMPTY");
        } else {
            System.out.println(tVItemQuantity.getText().toString());
        }

        // Enables button clicks
        iBIncreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, pos, 0);
            }
        });
        //iBDecreaseQuantity.setOnClickListener(this);

        return convertView1;
    }

    // Increases Items Quantity
    public void increaseQuantity(View view) {
        int quantity = Integer.parseInt(tVItemQuantity.getText().toString());
        updatedQuantity = (Integer.toString(++quantity));
        tVItemQuantity.setText(updatedQuantity);



    }

    public void decreaseTotal(View view) {
        int quantity = Integer.parseInt(tVItemQuantity.getText().toString());


        if (quantity > 0) {
            updatedQuantity = (Integer.toString(quantity--));
        }





    }

    private void addToShoopingCart(View view) {

    }

    /*@Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.grid_single_product_increase_quantity:

                increaseQuantity(view);
                //tVItemQuantity.setText(updatedQuantity);
                break;
            case R.id.grid_single_product_decrease_quantity:
                decreaseTotal(view);
                //tVItemQuantity.setText(updatedQuantity);
            default:
                break;
        }
    }*/
}
