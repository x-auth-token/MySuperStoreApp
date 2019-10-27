/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.data.FirebaseHelper;
import com.pm.mysuperstoreapp.utils.Utils;

import java.util.Objects;


// Registration form Activity
public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private EditText email;
    private EditText password;
    private EditText firstname;
    private EditText lastname;
    private ProgressBar progressBar;
    // private TextView notification;
    // private Button btnRegister;
    // private static final String TAG = "Firestore_add_new_user";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        initViews();


    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initViews() {
        firstname = findViewById(R.id.activity_register_et_fname);
        lastname = findViewById(R.id.activity_register_et_lname);
        email = findViewById(R.id.activity_register_et_email);
        password = findViewById(R.id.activity_register_et_password);
        progressBar = findViewById(R.id.activity_register_pb_progress);

    }

    // Allow user registration
    public void registerNewUser(View view) {
        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                // Prepare all needed user identification fields
                                user = mAuth.getCurrentUser();
                                String displayName = firstname.getText().toString() + " " + lastname.getText().toString();
                                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(displayName).build();
                                user.updateProfile(profileChangeRequest);
                                progressBar.setVisibility(View.VISIBLE);

                                // Send verification request to provided email
                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {


                                            progressBar.setVisibility(View.GONE);
                                            Utils.makeToast(findViewById(R.id.activity_register_et_fname), getString(R.string.registration_success) + " " + getString(R.string.email_sent) + email.getText().toString(), "#FFFFFF");

                                            // Move to Login Activity for login after verifying email
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    FirebaseHelper.saveUserToFirebase(user);
                                                    final Intent intent = new Intent(getApplicationContext(),
                                                            LoginActivity.class);
                                                    startActivity(intent);
                                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                                                    finish();
                                                }
                                            }, 3000);


                                        } else {
                                            progressBar.setVisibility(View.GONE);
                                            Utils.makeToast(findViewById(R.id.activity_register_et_fname), Objects.requireNonNull(task.getException()).getMessage(), "#FF0000");

                                        }
                                    }
                                });

                            } else {

                                Utils.makeToast(findViewById(R.id.activity_register_et_fname), Objects.requireNonNull(task.getException()).getMessage(), "#FF0000");

                            }

                        }
                    });

        } else {
            Utils.makeToast(findViewById(R.id.activity_register_et_fname), getString(R.string.not_all_fields_filled), "#FF0000");
        }

    }

    // Deal with hardware back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utils.goToLoginActivity(findViewById(android.R.id.content));
    }

    // Cancel and get back to login page
    public void cancelRegistration(View view) {
        Utils.goToLoginActivity(view);
    }

    // Validates email correctness
    public void emailInputValidation(View view) {

        if (Utils.isValidEmail(email, findViewById(R.id.activity_register_et_fname))) {
            email.clearFocus();
            password.requestFocus();
        }

    }

    
    public void firsNameInputValidation(View view) {

        if (Utils.isValidFirstLastName(firstname, findViewById(R.id.activity_register_et_fname))) {
            firstname.clearFocus();
            findViewById(R.id.activity_register_et_lname).requestFocus();
        }

    }

    public void lastNameInputValidation(View view) {

        if (Utils.isValidFirstLastName(lastname, findViewById(R.id.activity_register_et_lname))) {
            lastname.clearFocus();
            findViewById(R.id.activity_register_et_email).requestFocus();
        }

    }


}