package com.pm.mysuperstoreapp.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.activities.LoginActivity;
import com.pm.mysuperstoreapp.activities.MainActivity;
import com.pm.mysuperstoreapp.models.MyAccountViewModel;

import java.util.HashMap;

public class MyAccountFragment extends Fragment implements View.OnClickListener {

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 0;
    private static final int GALERY_REQUEST_CODE = 1;
    private static final String TAG = "MyAccPopUserProfile";
    private MyAccountViewModel mViewModel;
    private ImageView iViewAccountPhoto;
    FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private Button btnLogout;
    private GoogleSignInClient googleSignInClient;
    private TextView tViewDisplayName;
    private TextView tViewEmail;
    private FirebaseStorage firebaseStorage;
    private FirebaseFirestore db;
    private Button btnUpdateProducts;
    private ProductManagementFragment productManagementFragment;


    public static MyAccountFragment newInstance() {
        return new MyAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        /*iViewAccountPhoto = view.findViewById(R.id.fragment_my_account_photo);
        tViewDisplayName = view.findViewById(R.id.fragment_my_account_text_view_display_name);
        tViewEmail = view.findViewById(R.id.fragment_my_account_text_view_email);
        btnLogout = view.findViewById(R.id.fragment_my_account_logout_button);*/

        /*btnLogout.setOnClickListener(this);
        iViewAccountPhoto.setOnClickListener(this);*/



        initViews(view);

        setOnClickListeners(view);

        productManagementFragment = new ProductManagementFragment();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        user = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();




        if (user != null) {
            populateUserProfile();

        }

        return view;
    }

    private void initViews(View view) {
        iViewAccountPhoto = view.findViewById(R.id.fragment_my_account_photo);
        tViewDisplayName = view.findViewById(R.id.fragment_my_account_text_view_display_name);
        tViewEmail = view.findViewById(R.id.fragment_my_account_text_view_email);
        btnLogout = view.findViewById(R.id.fragment_my_account_logout_button);
        btnUpdateProducts = view.findViewById(R.id.fragment_my_account_update_products_button);
    }

    private void setOnClickListeners(View view) {
        iViewAccountPhoto.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnUpdateProducts.setOnClickListener(this);
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
                } else {


                }


            }
        };


        mViewModel = ViewModelProviders.of(this).get(MyAccountViewModel.class);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST_CODE:
                    if (getActivity() != null) {
                        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                        } else {
                            Uri selectedCameraImage = data.getData();
                            iViewAccountPhoto.setImageURI(selectedCameraImage);
                        }
                    }
                    break;
                case GALERY_REQUEST_CODE:
                    //TODO: add check if activity is null

                    Uri selectedImage = data.getData();
                    uploadProfilePhotoToFirebaseStorage(selectedImage);
                    //Glide.with(getContext()).load(selectedImage).into(iViewAccountPhoto);
                    iViewAccountPhoto.setImageURI(selectedImage);
                    break;
            }
        }
    }

    private void uploadProfilePhotoToFirebaseStorage(Uri selectedImage) {

        String uid = user.getUid();
        //String filePath = "profile.png";
        firebaseStorage = FirebaseStorage.getInstance();

        final StorageReference storageReference = firebaseStorage.getReference("Users/" + uid + "/" + "profile.jpeg");
        //storageReference.child("profile.jpeg");


        final UploadTask uploadTask = storageReference.putFile(selectedImage);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        saveURLToFirestore(uri.toString());
                    }
                });
            }
        });


    }

    private void saveURLToFirestore(String profilePictureURL) {
        String uid = user.getUid();
        db.collection("users").document(uid).update("profilePictureUrl", profilePictureURL).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully updated!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error updating document", e);
            }
        });
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

    // Gets picture from Galery or Camera and sets it as profile picture.
    private void setProfilePicture(View view) {

        final String[] options = {"Camera", "Gallery"};

        new AlertDialog.Builder(view.getContext()).setTitle(R.string.choose_picture_location).setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, CAMERA_REQUEST_CODE);
                        break;
                    case 1:
                        String[] mimeTypes = {"image/jpeg", "image/png", "image/jpg"};
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        pickPhoto.setType("image/*"); // Make sure that only image gets selected
                        pickPhoto.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes); // Only jpegs or pngs allowed
                        startActivityForResult(pickPhoto, GALERY_REQUEST_CODE);
                        break;


                }
                dialogInterface.dismiss();
            }

        }).show();


    }

    private void populateUserProfile() {

        FirebaseUser user = firebaseAuth.getCurrentUser();
        String uid = "";
        if (user != null) {
            uid = user.getUid();
        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();



        db.collection("users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot result = task.getResult();

                    if (result == null) {
                        Log.d(TAG, "onComplete: result is empty!");
                        return;
                    }

                    HashMap<String, Boolean> roles = (HashMap<String, Boolean>) result.get("role");

                    boolean isAdmin = false;

                    for (HashMap.Entry<String, Boolean> r : roles.entrySet()) {
                        if (r.getKey().equals("admin") && r.getValue().booleanValue()) {
                            isAdmin = true;
                        }
                    }
                    if (isAdmin) {

                        btnUpdateProducts.setVisibility(View.VISIBLE);
                        Log.d("AdminCheck:", "\n\n\n\n\n\n\nIS ADMIN! IT WORKED!!!\n\n\n\n\n\n");
                    }

                    String profilePictureUrl;

                    if (result.getString("profilePictureUrl") != null) {
                        profilePictureUrl = result.getString("profilePictureUrl");

                        if (!profilePictureUrl.isEmpty()) {
                            Glide.with(getContext()).load(profilePictureUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(iViewAccountPhoto);
                        }
                    }

                    tViewDisplayName.setText(result.getString("displayName"));
                    tViewEmail.setText(result.getString("email"));


                }
            }
        });


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
            case R.id.fragment_my_account_update_products_button:
                getFragmentManager().beginTransaction().hide(this).commit();
                getChildFragmentManager().beginTransaction().add(productManagementFragment, productManagementFragment.getClass().getSimpleName()).show(productManagementFragment).commit();
                break;
            default:
                break;
        }
    }
}
