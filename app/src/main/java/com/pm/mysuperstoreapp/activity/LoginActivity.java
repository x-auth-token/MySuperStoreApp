package com.pm.mysuperstoreapp.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

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


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private EditText email;
    private EditText password;
    private TextView notification;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = mAuth.getCurrentUser();
        email = findViewById(R.id.activity_register_et_email);
        password = findViewById(R.id.activity_register_et_password);
        notification = findViewById(R.id.activity_register_tv_notification);
        progressBar = findViewById(R.id.activity_register_pb_progress);

    }
    public void login(View view) {

        switch(view.getId()) {
            case R.id.activity_login_btn_email_login:
                emailLogin(view);
                break;
            case R.id.activity_login_btn_google_login:
                break;
            case R.id.activity_login_btn_facebook_login:
                break;
            case R.id.activity_login_btn_forgot_password:
                break;
            case R.id.activity_login_btn_register:
                Button b = (Button) view;
                b.setTextColor(Color.parseColor("#C0C0C0"));

                final Intent intent = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
                finish();
                break;
            default:
                break;
        }
    }

    private void emailLogin(View view) {

        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            user = mAuth.getCurrentUser();

                            if (!user.isEmailVerified()) {
                                progressBar.setVisibility(View.GONE);
                                notification.setTextColor(Color.parseColor("#FFFFFF"));
                                notification.setText("Please verify your email");
                            } else {
                                final Intent intent = new Intent(getApplicationContext(),
                                        MainActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                                finish();
                            }





                            //Toast.makeText(RegisterActivity.this, "REG_OK", Toast.LENGTH_SHORT).show();
                           /* final Intent intent = new Intent(getApplicationContext(),
                                    MainActivity.class);

                            startActivity(intent);
                            finish();*/

                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //Toast.LENGTH_SHORT).show();
                            //Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            notification.setTextColor(Color.parseColor("#FF0000"));
                            notification.setText(task.getException().getMessage().toString());
                        }

                        // ...
                    }
                });

    }

    private void googleLogin(View view) {

    }

    private void facebookLogin(View view) {

    }


}
