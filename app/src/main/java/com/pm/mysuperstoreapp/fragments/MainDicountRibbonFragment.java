package com.pm.mysuperstoreapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.activities.MainActivity;
import com.pm.mysuperstoreapp.adapters.MainDicountRibbonAdapter;
import com.pm.mysuperstoreapp.adapters.RecyclerViewCategoriesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainDicountRibbonFragment extends Fragment {

    private List<String> discountImagesUrls;
    public static final String TAG = "mytag";
    StorageReference storageRef;
    FirebaseStorage storage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_categories, container, false);

        populateRibbon(view);

        return view;
    }

    private void populateRibbon(final View view) {

        FirebaseFirestore.getInstance().collection("store").document("discounts").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot result = task.getResult();
                    if (result == null) {
                        Log.d(TAG, "onComplete: result is empty!");
                        return;
                    }


                    //final List<DocumentSnapshot> documents = result.getDocuments();

                    discountImagesUrls = new ArrayList<>();

                    Map<String, Object> map = task.getResult().getData();

                    if (map != null) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            discountImagesUrls.add(entry.getValue().toString());
                        }
                    }


                    ViewPager pagerAdapter = view.findViewById(R.id.fLDiscountRibbon);
                    pagerAdapter.setAdapter(new MainDicountRibbonAdapter(, discountImagesUrls));
                    //pagerAdapter.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    Log.d(TAG, "onComplete: ERROR could not fetch collection " + task.getException());
                }
            }
        });

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();



    }


}

