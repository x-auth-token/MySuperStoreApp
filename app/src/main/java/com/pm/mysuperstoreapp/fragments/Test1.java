package com.pm.mysuperstoreapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.models.Test1ViewModel;
import com.pm.mysuperstoreapp.models.TestViewModel;

public class Test1 extends Fragment {

    private Test1ViewModel mViewModel;

/*    public static ShopNowFragment newInstance() {
        return new ShopNowFragment();
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.test_fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Test1ViewModel.class);
        // TODO: Use the ViewModel
    }

}
