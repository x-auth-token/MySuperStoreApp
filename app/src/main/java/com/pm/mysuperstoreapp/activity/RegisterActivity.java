package com.pm.mysuperstoreapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pm.mysuperstoreapp.R;



public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private EditText email;
    private EditText password;
    private EditText firstname;
    private EditText lastname;
    private TextView notification;
    private ProgressBar progressBar;

    final boolean PASSED = true;
    final boolean FAILED = false;
    boolean status = false;



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




    public void registerNewUser(View view) {
            //if (status == PASSED) {
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
                                                notification.setTextColor(Color.parseColor("#FFFFFF"));
                                                notification.setText(getString(R.string.registration_success) + " Email sent to: " + email.getText().toString());

                                                final Intent intent = new Intent(getApplicationContext(),
                                                        LoginActivity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                                                finish();

                                            } else {
                                                notification.setTextColor(Color.parseColor("#FF0000"));
                                                notification.setText(task.getException().getMessage().toString());
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

            //} else {
            //    notification.setTextColor(Color.parseColor("#FF0000"));
            //    notification.setText(getString(R.string.not_all_fields_filled));
            //}
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

        if (email.equals("")) {

                notification.setTextColor(Color.parseColor("#FF0000"));
                notification.setText(getString(R.string.not_all_fields_filled));

            } else if (!isValidEmailFormat(email.getText().toString())) {

                notification.setTextColor(Color.parseColor("#FF0000"));
                notification.setText(getString(R.string.invalid_email_format));

            } else {

                status = PASSED;
            }


    }

    // Checks email format correctness
    private boolean isValidEmailFormat(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void saveUserToDB(FirebaseUser user) {

    }


    public void passwordInputValidation(View view) {

        if (password.equals("")) {
            notification.setTextColor(Color.parseColor("#FF0000"));
            notification.setText(getString(R.string.not_all_fields_filled));
        } else {
            status = PASSED;
        }

    }
}
