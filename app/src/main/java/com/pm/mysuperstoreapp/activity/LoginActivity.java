package com.pm.mysuperstoreapp.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pm.mysuperstoreapp.R;


public class LoginActivity extends AppCompatActivity {

   private FirebaseAuth mAuth;


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
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
    public void login(View view) {

        switch(view.getId()) {
            case R.id.activity_login_btn_email_login:
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

    }

    private void googleLogin(View view) {

    }

    private void facebookLogin(View view) {

    }


}
