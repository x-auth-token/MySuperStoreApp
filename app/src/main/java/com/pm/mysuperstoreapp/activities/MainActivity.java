package com.pm.mysuperstoreapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pm.mysuperstoreapp.R;

import com.pm.mysuperstoreapp.custom_views.NoSwipeViewPager;
import com.pm.mysuperstoreapp.fragments.FavoritesFragment;
import com.pm.mysuperstoreapp.fragments.MyAccountFragment;
import com.pm.mysuperstoreapp.fragments.ProductPageFragment;
import com.pm.mysuperstoreapp.fragments.ShopNowFragment;
import com.pm.mysuperstoreapp.fragments.ShoppingCartFragment;
import com.pm.mysuperstoreapp.utils.Utils;

import java.text.DateFormatSymbols;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    public static NoSwipeViewPager mMainViewPager;
    BottomNavigationView mMainNavigationBar;
    public static ViewPager mTopViewPager;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initMainFragmentManager(savedInstanceState);

        //showItemCountBadge(true);


    }


    @SuppressLint("ClickableViewAccessibility")
    /*private void initViewPager() {
        mMainViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mMainViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        //mTopViewPager.setAdapter(new ShopNowFlashDealsPagerAdapter(MainActivity.this, images));
    }*/

    /*private void initMainFragmentAdapter() {
        MainFragmentAdapter mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }*/

    private void initMainFragmentManager(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fLMainFragmentContainer, new ShopNowFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    private void initViews() {

      //imageView = findViewById(R.id.image);


        //mTopViewPager = findViewById(R.id.top_view_pager);

        //mMainViewPager = findViewById(R.id.main_view_pager);
        mMainNavigationBar = findViewById(R.id.bottom_navigation_menu_panel);
        mMainNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                navigateToFragment(menuItem);
                return true;
            }
        });

    }



    private boolean navigateToFragment(MenuItem menuItem) {
        int itemId = menuItem.getItemId();

        switch (itemId) {
            case R.id.nav_shop:
                //mTopViewPager.setCurrentItem(0, true);
                //mMainViewPager.setCurrentItem(0, true);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fLMainFragmentContainer, new ShopNowFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                return true;
            case R.id.nav_hot_deals:
               //mMainViewPager.setCurrentItem(1, true);
                //mTopViewPager.setCurrentItem(1, true);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fLMainFragmentContainer, new ProductPageFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.nav_favorites:
                if (isAuthenticated()) {
                    Utils.goToLoginActivity(findViewById(android.R.id.content));
                } else {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fLMainFragmentContainer, new FavoritesFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                return true;
            case R.id.nav_shopping_cart:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fLMainFragmentContainer, new ShoppingCartFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.nav_my_account:

                if (isAuthenticated()) {
                    Utils.goToLoginActivity(findViewById(android.R.id.content));
                } else {

                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fLMainFragmentContainer, new MyAccountFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
                return true;

            default:
                return false;



        }
    }

    private boolean isAuthenticated() {
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

        return (user == null) ? true : false;
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
