package com.pm.mysuperstoreapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.utils.Utils;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private EditText email;
    private EditText password;
    private EditText firstname;
    private EditText lastname;
    private TextView notification;
    private ProgressBar progressBar;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        firstname = findViewById(R.id.activity_register_et_fname);
        lastname = findViewById(R.id.activity_register_et_lname);
        email = findViewById(R.id.activity_register_et_email);
        password = findViewById(R.id.activity_register_et_password);
        //notification = findViewById(R.id.activity_register_tv_notification);
        progressBar = findViewById(R.id.activity_register_pb_progress);
        //btnRegister = findViewById(R.id.activity_register_btn_register);


    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    public void registerNewUser(View view) {
        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                user = mAuth.getCurrentUser();
                                progressBar.setVisibility(View.VISIBLE);
                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            progressBar.setVisibility(View.GONE);
                                            Utils.makeToast(findViewById(R.id.activity_register_et_fname), getString(R.string.registration_success) + " " + getString(R.string.email_sent) + email.getText().toString(), "#FFFFFF");

                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    final Intent intent = new Intent(getApplicationContext(),
                                                            LoginActivity.class);
                                                    startActivity(intent);
                                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                                                    finish();
                                                }
                                            }, 3000);


                                        } else {
                                            progressBar.setVisibility(View.GONE);
                                            Utils.makeToast(findViewById(R.id.activity_register_et_fname), task.getException().getMessage().toString(), "#FF0000");

                                        }
                                    }
                                });

                            } else {

                                Utils.makeToast(findViewById(R.id.activity_register_et_fname), task.getException().getMessage().toString(), "#FF0000");

                            }

                        }
                    });

        } else {
            Utils.makeToast(findViewById(R.id.activity_register_et_fname), getString(R.string.not_all_fields_filled), "#FF0000");
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utils.goToLoginActivity(findViewById(android.R.id.content));
    }

    // Cancel and get back to login page
    public void cancelRegistration(View view) {
        Utils.goToLoginActivity(view);
    }

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
