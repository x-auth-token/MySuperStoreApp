package com.pm.mysuperstoreapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.pm.mysuperstoreapp.fragments.HotDealsFragment;
import com.pm.mysuperstoreapp.fragments.MyAccountFragment;
import com.pm.mysuperstoreapp.fragments.ProductPageFragment;
import com.pm.mysuperstoreapp.fragments.ShopNowFragment;
import com.pm.mysuperstoreapp.fragments.ShoppingCartFragment;
import com.pm.mysuperstoreapp.utils.Utils;

public class MainActivity extends AppCompatActivity {



    BottomNavigationView mMainNavigationBar;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FirebaseAuth firebaseAuth;

    final Fragment shopNowFragment = new ShopNowFragment();
    final Fragment hotDealsFragment = new HotDealsFragment();
    final Fragment favoritesFragment = new FavoritesFragment();
    final Fragment shoppingCartFragment = new ShoppingCartFragment();
    final Fragment myAccountFragment = new MyAccountFragment();

    private static final String MY_ACCOUNT_FRAGMENT_TAG = "MY_ACCOUNT_FRAGMENT";
    private static final String SHOPPING_CART_FRAGMENT_TAG = "SHOPPING_CART_FRAGMENT";
    private static final String FAVORITES_FRAGMENT_TAG = "FAVORITES_FRAGMENT";
    private static final String HOT_DEALS_FRAGMENT_TAG = "HOT_DEALS_FRAGMENT";
    private static final String SHOP_NOW_FRAGMENT_TAG = "SHOP_NOW_FRAGMENT";

    Fragment active = shopNowFragment;



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
            //fragmentManager.beginTransaction().add(R.id.main_fragment_container, shopNowFragment).addToBackStack(SHOP_NOW_FRAGMENT_TAG).commit();
            replaceFragment(active, shopNowFragment);
            /*fragmentManager.beginTransaction().add(R.id.main_fragment_container, myAccountFragment, "MY_ACCOUNT_FRAGMENT").hide(myAccountFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_fragment_container, shoppingCartFragment, "SHOPPING_CART_FRAGMENT").hide(shoppingCartFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_fragment_container, favoritesFragment, "FAVORITES_FRAGMENT").hide(favoritesFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_fragment_container, hotDealsFragment, "HOT_DEALS_FRAGMENT").hide(hotDealsFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_fragment_container, shopNowFragment, "SHOP_NOW_FRAGMENT").commit();*/





        }
    }

    private void initViews() {


        mMainNavigationBar = findViewById(R.id.bottom_navigation_menu_panel);
        mMainNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                navigateToFragment(menuItem);
                return true;
            }
        });

    }

    private void replaceFragment(Fragment activeFragment, Fragment newFragment) {
        String fragmentTag = newFragment.getClass().getSimpleName();

        //boolean fragmentPopped = fragmentManager.popBackStackImmediate(fragmentTag, 0);
        //Fragment fragmentToSwitchTo = ;

        //Fragment fragmentToSwitchFrom = fragmentManager.findFragmentByTag(activeFragmentTag);

        if (fragmentManager.findFragmentByTag(fragmentTag) == null) {
            //fragmentToSwitchTo = newFragment;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main_fragment_container, newFragment, fragmentTag);
            fragmentTransaction.hide(activeFragment);
            fragmentTransaction.show(newFragment);
            fragmentTransaction.commit();
            active = newFragment;
        }  else {


            fragmentManager.beginTransaction().hide(activeFragment).show(newFragment).commit();
            active = newFragment;
        }
    }

    private boolean navigateToFragment(MenuItem menuItem) {
        int itemId = menuItem.getItemId();

        switch (itemId) {
            case R.id.nav_shop:

                //fragmentManager.beginTransaction().replace(R.id.main_fragment_container, shopNowFragment).addToBackStack(SHOP_NOW_FRAGMENT_TAG).commit();
                replaceFragment(active, shopNowFragment);
                /*fragmentManager.beginTransaction().hide(active).show(shopNowFragment).commit();
                active = shopNowFragment;*/

                return true;
            case R.id.nav_hot_deals:

                //fragmentManager.beginTransaction().replace(R.id.main_fragment_container, hotDealsFragment).addToBackStack(HOT_DEALS_FRAGMENT_TAG).commit();
                /*fragmentManager.beginTransaction().hide(active).show(hotDealsFragment).commit();
                active = hotDealsFragment;*/
                replaceFragment(active, hotDealsFragment);
                return true;
            case R.id.nav_favorites:
               /* if (!isAuthenticated()) {
                    Utils.goToLoginActivity(findViewById(android.R.id.content));
                } else {*/

                    //fragmentManager.beginTransaction().replace(R.id.main_fragment_container, favoritesFragment).addToBackStack(FAVORITES_FRAGMENT_TAG).commit();
                    /*fragmentManager.beginTransaction().hide(active).show(favoritesFragment).commit();
                    active = favoritesFragment;*/
               // }
                replaceFragment(active, favoritesFragment);
                return true;
            case R.id.nav_shopping_cart:

                //fragmentManager.beginTransaction().replace(R.id.main_fragment_container, shoppingCartFragment).addToBackStack(SHOPPING_CART_FRAGMENT_TAG).commit();
                /*fragmentManager.beginTransaction().hide(active).show(shoppingCartFragment).commit();
                active = shoppingCartFragment;*/
                replaceFragment(active, shoppingCartFragment);
                return true;
            case R.id.nav_my_account:

                /*if (!isAuthenticated()) {
                    Utils.goToLoginActivity(findViewById(android.R.id.content));
                } else {

*/
                    //fragmentManager.beginTransaction().replace(R.id.main_fragment_container, myAccountFragment).addToBackStack(MY_ACCOUNT_FRAGMENT_TAG).commit();
                    /*fragmentManager.beginTransaction().hide(active).show(myAccountFragment).commit();
                    active = myAccountFragment;*/

                //}
                replaceFragment(active, myAccountFragment);
                return true;

            default:
                return false;



        }
    }

    private boolean isAuthenticated() {
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

        return (user != null) ? true : false;
    }


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
