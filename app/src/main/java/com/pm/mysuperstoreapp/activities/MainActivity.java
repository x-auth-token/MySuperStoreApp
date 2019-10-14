package com.pm.mysuperstoreapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.adapters.MainFragmentAdapter;

import com.pm.mysuperstoreapp.custom_views.NoSwipeViewPager;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    public static NoSwipeViewPager mMainViewPager;
    BottomNavigationView mMainNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initViewPager();

        showItemCountBadge(true);


    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViewPager() {
        mMainViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mMainViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
    }

    private void initViews() {
       //imageView = findViewById(R.id.image);
        mMainViewPager = findViewById(R.id.main_view_pager);
        mMainNavigationBar = findViewById(R.id.bottom_navigation_menu_panel);
        mMainNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                navigateToFragment(menuItem);
                return true;
            }
        });
    }

    private void navigateToFragment(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.nav_shop:
                mMainViewPager.setCurrentItem(0, true);
                break;
            case R.id.nav_hot_deals:
                mMainViewPager.setCurrentItem(1, true);
                break;
            case R.id.nav_shopping_cart:
                mMainViewPager.setCurrentItem(3, true);

                break;

        }
    }

    private void showItemCountBadge(boolean show) {
        mMainNavigationBar = findViewById(R.id.bottom_navigation_menu_panel);
        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) mMainNavigationBar.getChildAt(0);

        View v = bottomNavigationMenuView.getChildAt(3);

        BottomNavigationItemView itemView = (BottomNavigationItemView) v;


        View badge = LayoutInflater.from(this)
                .inflate(R.layout.item_count_badge_layout, bottomNavigationMenuView, false);
        TextView tv = badge.findViewById(R.id.item_count_badge_1);
        tv.setText("22+");


        itemView.addView(badge);


        if (show) {

            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
        }

    }




}
