package com.pm.mysuperstoreapp.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.pm.mysuperstoreapp.R;


public class LoginActivity extends AppCompatActivity {

   private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }



    public void login(View view) {

    }
}
