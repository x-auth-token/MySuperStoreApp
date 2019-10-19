package com.pm.mysuperstoreapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pm.mysuperstoreapp.fragments.ShopNowFragment;
import com.pm.mysuperstoreapp.fragments.Test1;

public class MainFragmentAdapter extends FragmentPagerAdapter {

    public MainFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return new ShopNowFragment();
            case 1:
                return new Test1();
                /*
            case 2:

            case 3:

            case 4:


            default:
                return null;*/
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
