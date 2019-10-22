package com.pm.mysuperstoreapp.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.FacebookContentProvider;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApi;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.activities.LoginActivity;
import com.pm.mysuperstoreapp.activities.MainActivity;
import com.pm.mysuperstoreapp.models.MyAccountViewModel;

import static android.app.Activity.RESULT_OK;

public class MyAccountFragment extends Fragment implements View.OnClickListener {

    private MyAccountViewModel mViewModel;
    private ImageView accountPhoto;
    FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth firebaseAuth;
    private Button btnLogout;
    private GoogleSignInClient googleSignInClient;

    public static MyAccountFragment newInstance() {
        return new MyAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        accountPhoto = view.findViewById(R.id.fragment_my_account_photo);
        accountPhoto.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        btnLogout = view.findViewById(R.id.fragment_my_account_logout_button);
        btnLogout.setOnClickListener(this);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

                if (user == null) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                    getActivity().finish();
                }
            }
        };



        mViewModel = ViewModelProviders.of(this).get(MyAccountViewModel.class);
        //accountPhoto.setProfileId(FacebookContentProvider.getAttachmentUrl());
        // TODO: Use the ViewModel
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 0:
                if(resultCode == getActivity().RESULT_OK){
                    Uri selectedImage = data.getData();
                    //    Glide.with(getContext()).load(selectedImage).into(accountPhoto);
                    accountPhoto.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == getActivity().RESULT_OK){
                    Uri selectedImage = data.getData();
                    //Glide.with(getContext()).load(selectedImage).into(accountPhoto);
                    accountPhoto.setImageURI(selectedImage);
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(authStateListener);

    }

    @Override
    public void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
    }




    @Override
    public void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    private void logout(View view) {



        if (firebaseAuth != null) {
            firebaseAuth.signOut();
        }
        if (googleSignInClient != null) {
            googleSignInClient.signOut();
        }

        final Intent intent = new Intent(getContext(),
                MainActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
        getActivity().finish();
    }

    private void setProfilePicture(View view) {

        final String[] options = { "Camera", "Gallery"};

        new AlertDialog.Builder(view.getContext()).setTitle(R.string.choose_picture_location).setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 0);
                        break;
                    case 1:

                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, 1);
                        break;


                }
                dialogInterface.dismiss();
            }

        }).show();


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_my_account_logout_button:
                logout(view);
                break;
            case R.id.fragment_my_account_photo:
                setProfilePicture(view);
                break;
            default:
                break;
        }
    }
}
