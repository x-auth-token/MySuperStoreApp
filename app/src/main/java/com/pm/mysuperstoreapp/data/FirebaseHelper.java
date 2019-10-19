package com.pm.mysuperstoreapp.data;

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

        public void test() {

        }
    }