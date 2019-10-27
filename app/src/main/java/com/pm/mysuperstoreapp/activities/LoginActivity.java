/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 *
 * This is Login Page Activity
 */

package com.pm.mysuperstoreapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.data.FirebaseHelper;
import com.pm.mysuperstoreapp.utils.Utils;

import java.util.Arrays;
import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private EditText email;
    private EditText password;
    private ProgressBar progressBar;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private CallbackManager callbackManager;
    private LoginManager fbLoginManager;
    private Button btnEmailLogin;

    //private static final String TAG = "Firestore_add_new_user";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        initViews();

        password.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btnEmailLogin.performClick();
                    return true;
                }
                return false;
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();

        // Check if stable Internet connection exist
        if (!Utils.isNetworkConnected(findViewById(android.R.id.content))) {

            new AlertDialog.Builder(this).setTitle(R.string.no_internet_connection).setMessage(R.string.no_internet_connection_prompt).setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).setNegativeButton(R.string.dismiss, null).setIcon(android.R.drawable.ic_dialog_alert).show();


        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    firebaseAuthWithGoogle(account);
                }
            } catch (ApiException e) {
                Utils.makeToast(findViewById(R.id.activity_login_et_email), getString(R.string.google_auth_failed), "#FF0000");
            }
        }


    }

    // Initiate all the relevant views
    private void initViews() {

        mAuth = FirebaseAuth.getInstance();

        //
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        callbackManager = CallbackManager.Factory.create();
        fbLoginManager = LoginManager.getInstance();


        AppEventsLogger.activateApp(getApplication());
        // FirebaseUser user = mAuth.getCurrentUser();
        email = findViewById(R.id.activity_login_et_email);
        password = findViewById(R.id.activity_login_et_password);
        progressBar = findViewById(R.id.activity_login_pb_progress);
        btnEmailLogin = findViewById(R.id.activity_login_btn_email_login);
    }

    // Main login method for email/password authentication
    public void emailLogin(final View view) {


        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) { // Empty credentials validation

            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                user = mAuth.getCurrentUser();

                                // Show progress bar while working with user data
                                progressBar.setVisibility(View.VISIBLE);
                                user.reload();
                                if (!user.isEmailVerified()) { // User didn't answer to validation email that was sent during registration
                                    progressBar.setVisibility(View.GONE);

                                    // move to email reverification page
                                    final Intent resendEmailIntent = new Intent(getApplicationContext(),
                                            ResendVerificationEmailActivity.class);

                                    resendEmailIntent.putExtra("emailAddress", user.getEmail());
                                    resendEmailIntent.putExtra("user", user);

                                    startActivity(resendEmailIntent);
                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                                    finish();


                                } else {
                                    // All good, Start main activity
                                    Utils.goToMainActivity(findViewById(android.R.id.content));
                                }

                            } else {
                                // If sign in fails, display a message to the user.
                                progressBar.setVisibility(View.GONE);

                                Utils.makeToast(email, Objects.requireNonNull(task.getException()).getMessage(), "#FF0000");
                            }


                        }
                    });
        } else {
            Utils.makeToast(view, view.getResources().getString(R.string.not_all_fields_filled), "#FF0000");
        }

    }

    // Validates email address correctness
    public void emailInputValidation(View view) {

        if (Utils.isValidEmail(email, email)) {
            email.clearFocus();
            password.requestFocus();
        }

    }

    // Allows password reset
    public void resetPassword(View view) {
        Button b = (Button) view;
        b.setTextColor(Color.parseColor("#C0C0C0"));
        final Intent intent = new Intent(getApplicationContext(),
                ResetPasswordActivity.class);
        if (!email.getText().toString().equals("")) {
            intent.putExtra("emailAddress", email.getText().toString());
        }

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
        finish();
    }

    // Binds registration button to view
    public void registerNewUser(View view) {
        Button b = (Button) view;
        b.setTextColor(Color.parseColor("#C0C0C0"));
        final Intent intent = new Intent(getApplicationContext(),
                RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
        finish();
    }

    // Binds google login to view
    public void googleLogin(View view) {
        signInToGoogle();
    }


    // Allow login with google
    private void signInToGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    // Google login logic
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        progressBar.setVisibility(View.VISIBLE);


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                            // Saving relevant user data to Firebase Firestore
                            if (user != null) {
                                FirebaseHelper.saveUserToFirebase(user);
                            }
                            Utils.goToMainActivity(findViewById(android.R.id.content));

                        } else {
                            // If sign in fails, display a message to the user.
                            Utils.makeToast(findViewById(R.id.activity_login_et_email), getString(R.string.auth_failed), "#FF0000");
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    // Go back to main activity on hardware back button press
    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Utils.goToMainActivity(findViewById(android.R.id.content));
    }


    // Facebook login logic
    public void facebookLogin(View view) {
        fbLoginManager.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {

                            // Save relevant user data to Firebase Firestore
                            FirebaseHelper.saveUserToFirebase(user);
                        }

                        Utils.goToMainActivity(findViewById(android.R.id.content));

                    }

                    @Override
                    public void onCancel() {
                        Utils.goToLoginActivity(findViewById(android.R.id.content));
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Utils.makeToast(findViewById(android.R.id.content), getString(R.string.auth_failed), "#FF0000");
                    }
                });

        fbLoginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "default"));

    }



    /*
    // Moved to FirebaseHelper
    @Deprecated
    private void saveUserToFirebase(FirebaseUser user) {
        String uid = user.getUid();
        UserModel userModel = new UserModel(uid, email.getText().toString(), user.getDisplayName());

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
    }*/
}
