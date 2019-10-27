/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.models.ProductViewModel;
import com.pm.mysuperstoreapp.models.TestViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// Main ShopNow fragment
public class ShopNowFragment extends Fragment implements View.OnClickListener {


    public static FragmentManager childFragmentManager;
    //private TestViewModel mViewModel;
    private SearchView fCShopNowSearch;
    private ImageButton iBMeat, iBFish, iBBakery, iBFruits, iBVegetables, iBAlcohol, iBBeverages;
    private android.widget.FrameLayout fLFlashDealsBanner;
    private static final String TAG = "mytag";
    private static final String FLASH_DEALS_TAG = "flash_deals_fragment";
    private TextView tVGategoryTitle;
    private Toolbar toolBar;
    private ImageButton iBBack;
    private String CATEGORY_GRID_FRAGMENT_TAG = "category_grid_fragment";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_now, container, false);

        //mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        childFragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = childFragmentManager.beginTransaction();
        transaction.add(R.id.fLShopNowFlashDealsBannerFragment, new ShopNowFlashDealsFragment(), FLASH_DEALS_TAG);


        initViews(view);

        transaction.addToBackStack(null);
        transaction.commit();
        childFragmentManager.executePendingTransactions();


        return view;

    }

    private void initViews(View view) {
        iBMeat = view.findViewById(R.id.fCShopNowMeatImageButton);
        iBFish = view.findViewById(R.id.fCShopNowFishImageButton);
        iBFruits = view.findViewById(R.id.fCShopNowFruitsImageButton);
        iBBakery = view.findViewById(R.id.fCShopNowBakeryImageButton);
        iBVegetables = view.findViewById(R.id.fCShopNowVegetablesImageButton);
        iBAlcohol = view.findViewById(R.id.fCShopNowAlcoholImageButton);
        iBBeverages = view.findViewById(R.id.fCShopNowBeveragesImageButton);

        tVGategoryTitle = view.findViewById(R.id.tVCategoryTitle);
        toolBar = view.findViewById(R.id.tBarShopNowFragment);
        iBBack = view.findViewById(R.id.iBGoBack);

        FrameLayout fLShopNowMiddleFrameLayout = view.findViewById(R.id.fLShopNowMiddleFrameLayout);
        fLFlashDealsBanner = view.findViewById(R.id.fLShopNowFlashDealsBannerFragment);
        fCShopNowSearch = view.findViewById(R.id.fCShopNowSearch);

        iBMeat.setOnClickListener(this);
        iBFish.setOnClickListener(this);
        iBFruits.setOnClickListener(this);
        iBBakery.setOnClickListener(this);
        iBVegetables.setOnClickListener(this);
        iBAlcohol.setOnClickListener(this);
        iBBeverages.setOnClickListener(this);
        iBBack.setOnClickListener(this);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.iBGoBack) {

            goToMainFragment();
        } else {
            makeRoom(); // Remove Top Banner

            String category = null;

            // Switch between categories
            switch (v.getId()) {
                case R.id.fCShopNowMeatImageButton:

                    category = getString(R.string.meat);
                    updateCategoryTitle(category);
                    break;
                case R.id.fCShopNowFishImageButton:
                    category = getString(R.string.fish);
                    updateCategoryTitle(category);
                    break;
                case R.id.fCShopNowBakeryImageButton:

                    category = getString(R.string.bakery);
                    updateCategoryTitle(category);
                    break;
                case R.id.fCShopNowFruitsImageButton:
                    category = getString(R.string.fruits);
                    updateCategoryTitle(category);
                    break;
                case R.id.fCShopNowVegetablesImageButton:
                    category = getString(R.string.vegetables);
                    updateCategoryTitle(category);
                    break;
                case R.id.fCShopNowAlcoholImageButton:
                    category = getString(R.string.alcohol);
                    updateCategoryTitle(category);
                    break;
                case R.id.fCShopNowBeveragesImageButton:
                    category = getString(R.string.beverages);
                    updateCategoryTitle(category);
                    break;


            }

            if (category != null) {
                getItemList(category);
            }

        }
    }

    // Allows going back to this fragment after quiting from categories
    private void goToMainFragment() {
        FragmentTransaction transaction = childFragmentManager.beginTransaction();
        transaction.remove(Objects.requireNonNull(childFragmentManager.findFragmentByTag(CATEGORY_GRID_FRAGMENT_TAG))).commit();

        toolBar.setVisibility(View.INVISIBLE);
        tVGategoryTitle.setVisibility(View.GONE);
        iBBack.setVisibility(View.GONE);


        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) fCShopNowSearch.getLayoutParams();
        marginLayoutParams.setMargins(10, -50, 10, 0);
        fCShopNowSearch.setLayoutParams(marginLayoutParams);

        fLFlashDealsBanner.setVisibility(View.VISIBLE);
        transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fLShopNowFlashDealsBannerFragment, new ShopNowFlashDealsFragment(), FLASH_DEALS_TAG);
        transaction.addToBackStack(null);
        transaction.commit();
        childFragmentManager.executePendingTransactions();
    }

    // Show category name on top toolbar
    private void updateCategoryTitle(String category) {
        TextView categoryTitle;
        categoryTitle = Objects.requireNonNull(getView()).findViewById(R.id.tVCategoryTitle);
        categoryTitle.setText(category);
    }

    // Remove TOP Banner and make room for products
    private void makeRoom() {
        childFragmentManager.beginTransaction().remove(Objects.requireNonNull(getChildFragmentManager().findFragmentByTag(FLASH_DEALS_TAG))).commit();
        fLFlashDealsBanner.setVisibility(View.GONE);

        toolBar.setVisibility(View.VISIBLE);
        tVGategoryTitle.setVisibility(View.VISIBLE);
        iBBack.setVisibility(View.VISIBLE);

        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) fCShopNowSearch.getLayoutParams();
        marginLayoutParams.setMargins(10, 180, 10, 0);
        fCShopNowSearch.setLayoutParams(marginLayoutParams);
    }

    // Fetch products from Firestore
    private void getItemList(String category) {
        final List<ProductViewModel> itemList = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference store = db.collection(category.toLowerCase()); //store
        store.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot result = task.getResult();
                    if (result != null) {
                        List<DocumentSnapshot> documents = result.getDocuments();
                        for (DocumentSnapshot document : documents) {
                            Map<String, Object> data = document.getData();
                            if (data != null) {

                                String name = (String) data.get("name");
                                String price = (String) data.get("price");
                                String quantity = (String) data.get("quantity");
                                String image_url = (String) data.get("image_url");
                                String sub_category = (String) data.get("sub_category");

                                ProductViewModel item = new ProductViewModel();
                                item.setImageUrl(image_url);
                                item.setName(name);
                                item.setPrice(price);
                                item.setQuantity(quantity);
                                item.setSubCategory(sub_category);

                                itemList.add(item);
                                Log.d(TAG, "onComplete: " + item);
                            }

                        }
                    }

                }

                FragmentTransaction transaction = childFragmentManager.beginTransaction();
                transaction.replace(R.id.fLShopNowMiddleFrameLayout, new CategoryGridFragment(itemList), CATEGORY_GRID_FRAGMENT_TAG);
                transaction.addToBackStack(null);
                transaction.commit();
                childFragmentManager.executePendingTransactions();
            }
        });
    }
}
