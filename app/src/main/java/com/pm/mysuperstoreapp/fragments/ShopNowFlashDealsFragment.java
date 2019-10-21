package com.pm.mysuperstoreapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.adapters.ShopNowFlashDealsRecyclerViewAdapter;
import com.pm.mysuperstoreapp.adapters.ShopNowFlashDealsViewFlipperAdapter;
import com.pm.mysuperstoreapp.models.PictureViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator2;

public class ShopNowFlashDealsFragment extends Fragment {

    private List<String> discountImagesUrls;
    public static final String TAG = "mytag";
    private AdapterViewFlipper adapterViewFlipper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shop_now, container, false);
        populateBanner(view);


        return view;
    }

    private void populateBanner(final View view) {

        FirebaseFirestore.getInstance().collection("store").document("banner").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot result = task.getResult();
                    if (result == null) {
                        Log.d(TAG, "onComplete: result is empty!");
                        return;
                    }


                    //final List<DocumentSnapshot> documents = result.getDocuments();

                    //discountImagesUrls = new ArrayList<>();
                    List<PictureViewModel> pictures = new ArrayList<>();
                    Map<String, Object> map = task.getResult().getData();

                    if (map != null) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            pictures.add(new PictureViewModel(entry.getValue().toString(), entry.getKey()));
                        }
                    }

                    /*adapterViewFlipper = view.findViewById(R.id.fCShopNowAdapterFlipperView);
                    ShopNowFlashDealsViewFlipperAdapter adapter = new ShopNowFlashDealsViewFlipperAdapter(getContext(), pictures);
                    adapterViewFlipper.setAdapter(adapter);*/


                    RecyclerView recyclerView = view.findViewById(R.id.rVFlashDeals);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);


                    recyclerView.setAdapter(new ShopNowFlashDealsRecyclerViewAdapter(getActivity(), pictures));

                    /*RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(getActivity()) {
                        @Override
                        protected int getVerticalSnapPreference() {
                            return LinearSmoothScroller.SNAP_TO_END;
                        }
                    };

                    smoothScroller.setTargetPosition(pictures.size() + 1 );
                    layoutManager.startSmoothScroll(smoothScroller);*/

                    PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();

                    pagerSnapHelper.attachToRecyclerView(recyclerView);
                    CircleIndicator2 indicator = view.findViewById(R.id.fCShopNowCircleIndicator);
                    indicator.attachToRecyclerView(recyclerView,pagerSnapHelper);
                    recyclerView.getAdapter().registerAdapterDataObserver(indicator.getAdapterDataObserver());



                    /*ViewPager pagerAdapter = view.findViewById(R.id.fLFlashDeals);
                    pagerAdapter.setAdapter(new ShopNowFlashDealsPagerAdapter(getContext(), discountImagesUrls));*/
                    //pagerAdapter.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    Log.d(TAG, "onComplete: ERROR could not fetch collection " + task.getException());
                }
            }
        });





    }


}

