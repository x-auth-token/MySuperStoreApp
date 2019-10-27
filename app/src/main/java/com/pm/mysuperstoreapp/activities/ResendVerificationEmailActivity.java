/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.utils.Utils;

public class ResendVerificationEmailActivity extends AppCompatActivity {

    private TextView prompt;
    private FirebaseUser user;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resend_verification_email);

        initViews();

        Intent intent = getIntent();

        if (intent.hasExtra("emailAddress")) { // Show email address if provided on previous screen
            String emailAddress = intent.getStringExtra("emailAddress");

            prompt.setText(getString(R.string.resend_verification_email_prompt) + emailAddress + "\n" + getString(R.string.resend_verification_now));

        }

        if (intent.hasExtra("user")) {
            user = intent.getParcelableExtra("user");
        }


    }

    // Deal with hardware back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utils.goToLoginActivity(findViewById(android.R.id.content));
    }

    private void initViews() {
        prompt = findViewById(R.id.activity_resend_verification_tv_prompt);
    }

    // Main method to resend the verification email
    public void resendVerificationEmail(View view) {
        user.sendEmailVerification();
        Utils.makeToast(findViewById(R.id.activity_resend_verification_tv_prompt), getString(R.string.email_sent) + user.getEmail(), "#FFFFFF");

        Intent intent = new Intent(getApplicationContext(),
                LoginActivity.class);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
        finish();

    }

    public void cancel(View view) {
        Utils.goToLoginActivity(view);
    }
}
