/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pm.mysuperstoreapp.models.UserModel;

// Helper class for Firebase Integration
public abstract class FirebaseHelper {
    private static final String TAG = "FIRESTORE_HELPER";


    // Save user to "users" collection on firestore
    public static void saveUserToFirebase(FirebaseUser user) {
        String uid = user.getUid();
        UserModel userModel = new UserModel(uid, user.getEmail(), user.getDisplayName());

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(uid).set(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error writing document", e);
            }
        });


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
                            pagerAdapter.setAdapter(new ShopNowFlashDealsPagerAdapter(getContext(), discountImagesUrls));
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
                    pagerAdapter.setAdapter(new ShopNowFlashDealsPagerAdapter(getContext(), discountImagesUrls));
                    //pagerAdapter.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    Log.d(TAG, "onComplete: ERROR could not fetch collection " + task.getException());
                }

            }
        }*/

    }
}