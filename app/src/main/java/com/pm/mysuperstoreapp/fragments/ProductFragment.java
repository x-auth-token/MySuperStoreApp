package com.pm.mysuperstoreapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.models.ProductViewModel;

public class ProductFragment extends Fragment {

    private ProductViewModel product;

    private TextView mTvProductName, mTvDescription, mTvPrice;
    private ImageView mIvProductImage;
    private Button mBtnAddProduct;

    public ProductFragment(ProductViewModel productViewModel) {
        product = productViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_now_product, container, false);
        initViews(view);
        populateFragment();
        return view;
    }

    private void populateFragment() {
        mTvProductName.setText(product.getName());
        mTvPrice.setText(String.valueOf(product.getPrice()));
        mTvDescription.setText(product.getDescription());
        mBtnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked to add " + product.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(View view) {
        mTvDescription = view.findViewById(R.id.tVProductDescription);
        mTvProductName = view.findViewById(R.id.tVProductName);
        mTvPrice = view.findViewById(R.id.tVProductPrice);
        mBtnAddProduct = view.findViewById(R.id.btnBuyAdd);
        mIvProductImage = view.findViewById(R.id.iVProductImage);
    }
}
