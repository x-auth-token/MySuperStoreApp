package com.pm.mysuperstoreapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.utils.Utils;

public class ResendVerificationEmailActivity extends AppCompatActivity {

    private String emailAddress;
    private TextView prompt;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resend_verification_email);

        prompt = findViewById(R.id.activity_resend_verification_tv_prompt);

        Intent intent = getIntent();

        if (intent.hasExtra("emailAddress")) {
            emailAddress = intent.getStringExtra("emailAddress");

            prompt.setText(getString(R.string.resend_verification_email_prompt) + emailAddress + "\n" + getString(R.string.resend_verification_now) );

        }

        if (intent.hasExtra("user")) {
            user = intent.getParcelableExtra("user");
        }




    }

    public void resendVerificationEmail(View view) {
        user.sendEmailVerification();
        Utils.makeToast(findViewById(R.id.activity_resend_verification_tv_prompt), getString(R.string.email_sent) + user.getEmail().toString(), "#FFFFFF");

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
