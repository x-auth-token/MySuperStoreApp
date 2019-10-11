package com.pm.mysuperstoreapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        firstname = findViewById(R.id.activity_register_et_fname);
        lastname = findViewById(R.id.activity_register_et_lname);
        email = findViewById(R.id.activity_register_et_email);
        password = findViewById(R.id.activity_register_et_password);
        notification = findViewById(R.id.activity_register_tv_notification);
        progressBar = findViewById(R.id.activity_register_pb_progress);


    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    public void registerNewUser(View view) {
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
                                        //Utils.isValidEmail(email, findViewById(R.id.activity_register_et_fname));
                                        //Utils.makeToast(findViewById(R.id.activity_register_et_fname), getString(R.string.registration_success) + getString(R.string.registration_success), "#FFFFFF");
                                        //notification.setTextColor(Color.parseColor("#FFFFFF"));
                                        // notification.setText(getString(R.string.registration_success) + " Email sent to: " + email.getText().toString());
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                final Intent intent = new Intent(getApplicationContext(),
                                                        LoginActivity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                                                finish();
                                            }
                                        }, 5000);


                                    } else {
                                        progressBar.setVisibility(View.GONE);
                                        Utils.makeToast(findViewById(R.id.activity_register_et_fname), getString(R.string.registration_success) + getString(R.string.registration_success), "#FF0000");

                                        //notification.setTextColor(Color.parseColor("#FF0000"));
                                        //notification.setText(task.getException().getMessage().toString());
                                    }
                                }
                            });

                        } else {

                            notification.setTextColor(Color.parseColor("#FF0000"));
                            notification.setText(task.getException().getMessage().toString());
                        }

                        // ...
                    }
                });



}

    // Cancel and get back to login page
    public void cancelRegistration(View view) {
        final Intent intent = new Intent(getApplicationContext(),
                LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
        finish();

    }

    public void emailInputValidation(View view) {

        boolean status;

        status = Utils.isValidEmail(email, findViewById(R.id.activity_register_et_fname));

        if (status) {
            email.clearFocus();
            password.requestFocus();
        }

    }

     public void passwordInputValidation(View view) {

        boolean status;

        status = Utils.isValidPassword(password, findViewById(R.id.activity_register_et_fname));

        if (status) {
            password.clearFocus();
            findViewById(R.id.activity_register_btn_register).requestFocus();
        }

    }

    public void firsNameInputValidation(View view) {

        boolean status;

        status = Utils.isValidFirstLastName(firstname, findViewById(R.id.activity_register_et_fname));

        if (status) {
            firstname.clearFocus();
            findViewById(R.id.activity_register_et_lname).requestFocus();
        }

    }

    public void lastNameInputValidation(View view) {

        boolean status;

        status = Utils.isValidFirstLastName(lastname, findViewById(R.id.activity_register_et_lname));

        if (status) {
            lastname.clearFocus();
            findViewById(R.id.activity_register_et_email).requestFocus();
        }

    }


}
