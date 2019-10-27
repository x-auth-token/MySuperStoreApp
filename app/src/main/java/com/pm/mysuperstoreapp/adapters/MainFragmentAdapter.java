/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pm.mysuperstoreapp.fragments.ShopNowFragment;

@Deprecated
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
             default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
