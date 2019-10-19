package com.pm.mysuperstoreapp.data;

import android.util.Log;

import androidx.annotation.NonNull;
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

public class FirebaseHelper {


    /*final CollectionReference dbStoreCollection = FirebaseFirestore.getInstance().collection("store");
    final DocumentReference docStoreDiscountsDocument = dbStoreCollection.document("discounts");

        dbStoreCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete (@NonNull Task < QuerySnapshot > task) {
            if (task.isSuccessful()) {


                QuerySnapshot dbResult = task.getResult();

                if (dbResult == null) {
                    Log.d(TAG, "onComplete: result is empty!");
                    return;
                }

                final List<DocumentSnapshot> documents = dbResult.getDocuments();
                List<String> categories = new ArrayList<>();
                for (DocumentSnapshot document : documents) {
                    String name = document.getString("name");

                    String id = document.getReference().getId();

                    categories.add(name);
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
                    for(
                    DocumentSnapshot document :documents)

                    {
                        String name = document.getString("name");

                        String id = document.getReference().getId();

                        categories.add(name);
                    }



                    LinearLayoutManager(getContext()));
                } else{
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
        }*/

        public void test() {

        }
    }