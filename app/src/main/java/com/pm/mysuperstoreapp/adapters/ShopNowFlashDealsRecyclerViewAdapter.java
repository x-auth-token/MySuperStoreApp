package com.pm.mysuperstoreapp.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterViewFlipper;
import android.widget.ImageView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.models.PictureViewModel;

import java.util.List;


public class ShopNowFlashDealsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<PictureViewModel> pictures;
    Context context;
 


    public ShopNowFlashDealsRecyclerViewAdapter(Context context, List<PictureViewModel> pictures) {
        this.pictures = pictures;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View inflatedRowView = layoutInflater.inflate(R.layout.fragment_shop_now_flash_deals, parent, false);

        inflatedRowView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));




        return new FlashDealsViewHolder(inflatedRowView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FlashDealsViewHolder flashDealsViewHolder = (FlashDealsViewHolder) holder;

        //((FlashDealsViewHolder) holder).iVFlashDealsPicture.setImageResource(R.drawable.apple);


        Glide.with(this.context).load(pictures.get(position).getUrl()).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(R.drawable.grain).into(flashDealsViewHolder.getPicture());



        flashDealsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* FragmentTransaction transaction = ShopNowFragment.childFragmentManager.beginTransaction();

                HotDealsFragment hotDealsFragment = new HotDealsFragment();

                Bundle bundle = new Bundle();
                //TODO change to pass category id instead of string
                bundle.putString(HotDealsFragment.argCategoryName, category);

                productFragment.setArguments(bundle);

                transaction.replace(R.id.fragment_shop_now_flash_deals, productFragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size();

    }

    private class FlashDealsViewHolder extends RecyclerView.ViewHolder {


        private ImageView iVFlashDealsPicture;


        FlashDealsViewHolder(@NonNull View itemView) {
            super(itemView);

            this.iVFlashDealsPicture = itemView.findViewById(R.id.iVFlashDealsPicture);
            //adapterViewFlipper = inflatedAdapterViewFlipper.findViewById(R.id.fCShopNowAdapterFlipperView);
            ShopNowFlashDealsViewFlipperAdapter adapter = new ShopNowFlashDealsViewFlipperAdapter(context, pictures);
            //adapterViewFlipper.setAdapter(adapter);
        }

        public ImageView getPicture() {
            return this.iVFlashDealsPicture;
        }



    }
}
