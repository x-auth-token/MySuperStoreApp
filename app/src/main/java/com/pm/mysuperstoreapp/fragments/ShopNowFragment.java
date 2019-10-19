package com.pm.mysuperstoreapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.models.TestViewModel;

public class ShopNowFragment extends Fragment {


    public static FragmentManager childFragmentManager;
    private TestViewModel mViewModel;


/*    public static ShopNowFragment newInstance() {
        return new ShopNowFragment();
    }*/


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_now, container, false);
        //return inflater.inflate(R.layout.fragment_shop_now_flash_deals, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        //childFragmentManager = getChildFragmentManager();
        //FragmentTransaction transaction = childFragmentManager.beginTransaction();
        //transaction.add(R.id.constraintLayout, new RecyclerViewCategoriesFragment());
        //transaction.add(R.id.fLFlashDeals, new ShopNowFlashDealsFragment());



        //transaction.addToBackStack(null);
        //transaction.commit();
        //childFragmentManager.executePendingTransactions();
        // TODO: Use the ViewModel
    }

}
