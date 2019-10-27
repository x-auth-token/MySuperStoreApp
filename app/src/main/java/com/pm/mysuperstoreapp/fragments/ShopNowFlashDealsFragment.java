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
import android.widget.AdapterViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.adapters.ShopNowFlashDealsPagerAdapter;
import com.pm.mysuperstoreapp.models.PictureViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

// Flash deals Fragment
public class ShopNowFlashDealsFragment extends Fragment {

    private List<String> discountImagesUrls;
    private static final String TAG = "mytag";
    private AdapterViewFlipper adapterViewFlipper;
    private ViewPager viewPager;
    private List<PictureViewModel> pictures;
    private Timer timer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shop_now, container, false);
        populateBanner(view);

        timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);


        return view;
    }

    // Adds images to the banner
    private void populateBanner(final View view) {

        // Fetches URLs from Firestore
        FirebaseFirestore.getInstance().collection("banner").document("banner").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot result = task.getResult();
                    if (result == null) {
                        Log.d(TAG, "onComplete: result is empty!");
                        return;
                    }

                    pictures = new ArrayList<>();
                    Map<String, Object> map = task.getResult().getData();

                    // Create Images
                    if (map != null) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            pictures.add(new PictureViewModel(entry.getValue().toString(), entry.getKey()));
                        }
                    }


                    viewPager = view.findViewById(R.id.fragment_shop_now_flash_deals_view_pager);
                    viewPager.setAdapter(new ShopNowFlashDealsPagerAdapter(Objects.requireNonNull(getContext()), pictures));

                    CircleIndicator indicator = view.findViewById(R.id.fCShopNowCircleIndicator);
                    indicator.setViewPager(viewPager);
                    Objects.requireNonNull(viewPager.getAdapter()).registerDataSetObserver(indicator.getDataSetObserver());


                } else {
                    Log.d(TAG, "onComplete: ERROR could not fetch collection " + task.getException());
                }
            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    // Switches images periodically
    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < pictures.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}

