package com.pm.mysuperstoreapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.pm.mysuperstoreapp.utils.Utils;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private EditText email;
    private EditText password;
    private TextView notification;
    private ProgressBar progressBar;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private CallbackManager callbackManager;
    private LoginManager fbLoginManager;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
    }



    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = mAuth.getCurrentUser();
        email = findViewById(R.id.activity_login_et_email);
        password = findViewById(R.id.activity_login_et_password);
        progressBar = findViewById(R.id.activity_login_pb_progress);
        btnLogin = findViewById(R.id.activity_login_btn_email_login);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Utils.makeToast(findViewById(R.id.activity_login_et_email), getString(R.string.google_auth_failed), "#FF0000");
            }
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    public void emailLogin(final View view) {

        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {

            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                user = mAuth.getCurrentUser();
                                progressBar.setVisibility(View.VISIBLE);
                                user.reload();
                                if (!user.isEmailVerified()) {
                                    progressBar.setVisibility(View.GONE);

                                    final Intent resendEmailIntent = new Intent(getApplicationContext(),
                                                    ResendVerificationEmailActivity.class);

                                            resendEmailIntent.putExtra("emailAddress", user.getEmail().toString());
                                            resendEmailIntent.putExtra("user", user);

                                            startActivity(resendEmailIntent);
                                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                                            finish();



                                } else {
                                    Utils.goToMainActivity(findViewById(android.R.id.content));
                                }

                            } else {
                                // If sign in fails, display a message to the user.
                                progressBar.setVisibility(View.GONE);
                                Utils.makeToast(email, task.getException().getMessage().toString(), "#FF0000");
                            }


                        }
                    });
        } else {
            Utils.makeToast(view, view.getResources().getString(R.string.not_all_fields_filled), "#FF0000");
        }

    }

    public void emailInputValidation(View view) {

       if (Utils.isValidEmail(email, email)) {
            email.clearFocus();
            password.requestFocus();
        }

    }

    public void resetPassword(View view) {
        Button b = (Button) view;
        b.setTextColor(Color.parseColor("#C0C0C0"));
        final Intent intent = new Intent(getApplicationContext(),
                ResetPasswordActivity.class);
        if (!email.equals("")) {
            intent.putExtra("emailAddress", email.getText().toString());
        }

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
        finish();
    }

    public void registerNewUser(View view) {
        Button b = (Button) view;
        b.setTextColor(Color.parseColor("#C0C0C0"));
        final Intent intent = new Intent(getApplicationContext(),
                RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
        finish();
    }

    public void googleLogin(View view) {
        signInToGoogle();
    }

    private void signInToGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        progressBar.setVisibility(View.VISIBLE);


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            Utils.goToMainActivity(findViewById(android.R.id.content));

                        } else {
                            // If sign in fails, display a message to the user.
                            Utils.makeToast(findViewById(R.id.activity_login_et_email), getString(R.string.auth_failed), "#FF0000");
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    public void facebookLogin(View view) {
        fbLoginManager.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
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

        fbLoginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends"));

    }


}
