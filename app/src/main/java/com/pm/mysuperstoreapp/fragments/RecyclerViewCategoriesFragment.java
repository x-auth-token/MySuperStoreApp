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
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.adapters.MainDicountRibbonAdapter;
import com.pm.mysuperstoreapp.adapters.RecyclerViewCategoriesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecyclerViewCategoriesFragment extends Fragment {

    private List<String> categories;
    public static final String TAG = "mytag";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_categories, container, false);

        populateCategories(view);

        return view;
    }


    private void populateCategories(final View view) {
        /*FirebaseFirestore.getInstance().collection("store").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot result = task.getResult();
                    if (result == null) {
                        Log.d(TAG, "onComplete: result is empty!");
                        return;
                    }
                    final List<DocumentSnapshot> documents = result.getDocuments();
                    List<String> categories = new ArrayList<>();
                    for (DocumentSnapshot document : documents) {
                        String name = document.getString("name");

                        String id = document.getReference().getId();

                        categories.add(name);
                    }
                    RecyclerView recyclerView = view.findViewById(R.id.rVShopNow);
                    recyclerView.setAdapter(new RecyclerViewCategoriesAdapter(categories));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    Log.d(TAG, "onComplete: ERROR could not fetch collection " + task.getException());
                }
            }
        });*/
        final CollectionReference dbStoreCollection = FirebaseFirestore.getInstance().collection("store");
        final DocumentReference docStoreDiscountsDocument = dbStoreCollection.document("discounts");

        dbStoreCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {


                    QuerySnapshot dbResult = task.getResult();
                    if (dbResult == null) {
                        Log.d(TAG, "onComplete: result is empty!");
                        return;
                    }

                    docStoreDiscountsDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot result = task.getResult();
                                if (result == null) {
                                    Log.d(TAG, "onComplete: result is empty!");
                                    return;
                                }


                                //final List<DocumentSnapshot> documents = result.getDocuments();

                                List<String> discountImagesUrls = new ArrayList<>();

                                Map<String, Object> map = task.getResult().getData();

                                if (map != null) {
                                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                                        discountImagesUrls.add(entry.getValue().toString());
                                    }
                                }


                                ViewPager pagerAdapter = view.findViewById(R.id.fLDiscountRibbon);
                                pagerAdapter.setAdapter(new MainDicountRibbonAdapter(getContext(), discountImagesUrls));
                                //pagerAdapter.setLayoutManager(new LinearLayoutManager(getContext()));
                            } else {
                                Log.d(TAG, "onComplete: ERROR could not fetch collection " + task.getException());
                            }

                        }
                    final List<DocumentSnapshot> documents = dbResult.getDocuments();
                    List<String> categories = new ArrayList<>();
                    for (DocumentSnapshot document : documents) {
                        String name = document.getString("name");

                        String id = document.getReference().getId();

                        categories.add(name);
                    }
                    RecyclerView recyclerView = view.findViewById(R.id.rVShopNow);
                    recyclerView.setAdapter(new RecyclerViewCategoriesAdapter(categories));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    Log.d(TAG, "onComplete: ERROR could not fetch collection " + task.getException());
                }
            }
        });


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

                    List<String> discountImagesUrls = new ArrayList<>();

                    Map<String, Object> map = task.getResult().getData();

                    if (map != null) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            discountImagesUrls.add(entry.getValue().toString());
                        }
                    }


                    ViewPager pagerAdapter = view.findViewById(R.id.fLDiscountRibbon);
                    pagerAdapter.setAdapter(new MainDicountRibbonAdapter(getContext(), discountImagesUrls));
                    //pagerAdapter.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    Log.d(TAG, "onComplete: ERROR could not fetch collection " + task.getException());
                }

    }
}
