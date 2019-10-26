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

import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.models.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryGridAdapter extends BaseAdapter  {

    private List<ProductViewModel> itemList;
    private LayoutInflater layoutInflater;
    private ImageButton iBIncreaseQuantity;
    private ImageButton iBDecreaseQuantity;
    private ImageButton iBAddToShoppingCart;
    private TextView tVItemQuantity;
    private View convertView;
    public static final String DEFAULT_QUANTITY = "0";
    private ImageView iVProductImage;
    private String updatedQuantity = "0";

    public CategoryGridAdapter(Context context) {

        itemList = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }


    public void addItems(List<ProductViewModel> itemList) {
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
    public View getView(int position, View convertView, final ViewGroup parent) {
        Log.d("mytag", "getView: " + position);

        this.convertView = convertView;
        final int pos = position;

        if (this.convertView == null) {
            this.convertView = layoutInflater.inflate(R.layout.grid_single_product, parent, false);
            //ImageButton iBtnCategoryButton = this.convertView.findViewById(R.id.iBGridSingleImage);
            iVProductImage  = this.convertView.findViewById(R.id.iBGridSingleImage);
            TextView tvName = this.convertView.findViewById(R.id.tVGridSingleName);
            TextView tvPrice = this.convertView.findViewById(R.id.tVGridSinglePrice);

            iBIncreaseQuantity = this.convertView.findViewById(R.id.grid_single_product_increase_quantity);
            iBDecreaseQuantity = this.convertView.findViewById(R.id.grid_single_product_decrease_quantity);
            iBAddToShoppingCart = this.convertView.findViewById(R.id.grid_single_product_add_to_shopping_cart);
            tVItemQuantity = this.convertView.findViewById(R.id.grid_single_product_textview_item_quantity);


            Glide.with(this.convertView).load(itemList.get(position).getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(iVProductImage);
            tvName.setText(itemList.get(position).getName());
            tvPrice.setText(itemList.get(position).getPrice());
        }

        if (tVItemQuantity.getText().toString().isEmpty()) {
            tVItemQuantity.setText(DEFAULT_QUANTITY);

            System.out.println("EMPTY TEST EMPTY");
        } else {
            System.out.println(tVItemQuantity.getText().toString());
        }

        iBIncreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, pos, 0);
            }
        });
        //iBDecreaseQuantity.setOnClickListener(this);

        return this.convertView;
    }

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
