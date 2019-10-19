package com.pm.mysuperstoreapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pm.mysuperstoreapp.R;

import com.pm.mysuperstoreapp.custom_views.NoSwipeViewPager;
import com.pm.mysuperstoreapp.fragments.ProductPageFragment;
import com.pm.mysuperstoreapp.fragments.ShopNowFragment;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    public static NoSwipeViewPager mMainViewPager;
    BottomNavigationView mMainNavigationBar;
    public static ViewPager mTopViewPager;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    //int images[] = {R.drawable.apple, R.drawable.banana, R.drawable.orange};


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
        //mTopViewPager.setAdapter(new MainDicountRibbonAdapter(MainActivity.this, images));
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
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fLMainFragmentContainer, new ProductPageFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.nav_shopping_cart:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fLMainFragmentContainer, new ProductPageFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.nav_my_account:

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fLMainFragmentContainer, new ProductPageFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


                return true;
            default:
                return false;
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
