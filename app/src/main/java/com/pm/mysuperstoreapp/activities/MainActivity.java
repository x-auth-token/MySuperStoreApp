/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.fragments.FavoritesFragment;
import com.pm.mysuperstoreapp.fragments.HotDealsFragment;
import com.pm.mysuperstoreapp.fragments.MyAccountFragment;
import com.pm.mysuperstoreapp.fragments.ProductManagementFragment;
import com.pm.mysuperstoreapp.fragments.ShopNowFragment;
import com.pm.mysuperstoreapp.fragments.ShoppingCartFragment;
import com.pm.mysuperstoreapp.fragments.dummy.DummyContent;


// Main Fragment Container Activity
public class MainActivity extends AppCompatActivity implements ProductManagementFragment.OnListFragmentInteractionListener {


    BottomNavigationView mMainNavigationBar;

    private FragmentManager fragmentManager;
    /*private FragmentTransaction fragmentTransaction;
    private FirebaseAuth firebaseAuth;*/

    final Fragment shopNowFragment = new ShopNowFragment();
    final Fragment hotDealsFragment = new HotDealsFragment();
    final Fragment favoritesFragment = new FavoritesFragment();
    final Fragment shoppingCartFragment = new ShoppingCartFragment();
    final Fragment myAccountFragment = new MyAccountFragment();

    /*private static final String MY_ACCOUNT_FRAGMENT_TAG = "MY_ACCOUNT_FRAGMENT";
    private static final String SHOPPING_CART_FRAGMENT_TAG = "SHOPPING_CART_FRAGMENT";
    private static final String FAVORITES_FRAGMENT_TAG = "FAVORITES_FRAGMENT";
    private static final String HOT_DEALS_FRAGMENT_TAG = "HOT_DEALS_FRAGMENT";
    private static final String SHOP_NOW_FRAGMENT_TAG = "SHOP_NOW_FRAGMENT";*/

    Fragment active = shopNowFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initMainFragmentManager(savedInstanceState);

        showItemCountBadge(true);


    }

    // Initialize FragmenManager
    private void initMainFragmentManager(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            replaceFragment(active, shopNowFragment);
        }
    }

    // Initialize views
    private void initViews() {


        mMainNavigationBar = findViewById(R.id.bottom_navigation_menu_panel);
        mMainNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                navigateBottomView(menuItem);
                return true;
            }
        });

    }

    // Allows switching and reusing fragments
    private void replaceFragment(Fragment activeFragment, Fragment newFragment) {
        String fragmentTag = newFragment.getClass().getSimpleName();

        // Create new fragment if doesn't exist
        if (fragmentManager.findFragmentByTag(fragmentTag) == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main_fragment_container, newFragment, fragmentTag);
            fragmentTransaction.hide(activeFragment);
            fragmentTransaction.show(newFragment);
            fragmentTransaction.commit();
            active = newFragment;
        } else {

            // Reuse existing
            fragmentManager.beginTransaction().hide(activeFragment).show(newFragment).commit();
            active = newFragment;
        }
    }

    // Allows navigation between various menu options
    private boolean navigateBottomView(MenuItem menuItem) {
        int itemId = menuItem.getItemId();

        switch (itemId) {
            case R.id.nav_shop:

                replaceFragment(active, shopNowFragment);
                return true;
            case R.id.nav_hot_deals:


                replaceFragment(active, hotDealsFragment);
                return true;
            case R.id.nav_favorites:

                replaceFragment(active, favoritesFragment);
                return true;
            case R.id.nav_shopping_cart:


                replaceFragment(active, shoppingCartFragment);
                return true;
            case R.id.nav_my_account:

                replaceFragment(active, myAccountFragment);
                return true;

            default:
                return false;


        }
    }


    /*
    @Deprecated // Not used anymore
    private boolean isAuthenticated() {
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

        return (user != null) ? true : false;
    }*/

    // Dealing with hardware back button
    @Override
    public void onBackPressed() {

        if (mMainNavigationBar.getSelectedItemId() != R.id.nav_shop) {
            mMainNavigationBar.setSelectedItemId(R.id.nav_shop);
        } else {
            new AlertDialog.Builder(this).setTitle(R.string.exit_warning).setMessage(R.string.exit_prompt).setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finishAffinity();
                }
            }).setNegativeButton(R.string.dismiss, null).show();


        }

    }

    // Shows Shopping card item count ( Shopping cart not implemented yet)
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


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
