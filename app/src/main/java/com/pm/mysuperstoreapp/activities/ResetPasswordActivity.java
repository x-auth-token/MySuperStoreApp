package com.pm.mysuperstoreapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.utils.Utils;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText email;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        email = findViewById(R.id.activity_passreset_et_email);
        progressBar = findViewById(R.id.activity_passreset_pb_progress);

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();

        if (intent.hasExtra("emailAddress")) {
            email.setText(intent.getStringExtra("emailAddress"));
        }

    }

    public void emailInputValidation(View view) {

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        if (Utils.isValidEmail(email, email)) {
            inputManager.hideSoftInputFromWindow(email.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

        }

    }

    public void resetPassword(View view) {


        String emailAddress = email.getText().toString();

        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {


                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Utils.makeToast(findViewById(R.id.activity_passreset_et_email), getString(R.string.email_sent) + email.getText().toString(), "#FFFFFF");

                            final Intent intent = new Intent(getApplicationContext(),
                                    LoginActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                            finish();

                        } else {
                            Utils.makeToast(findViewById(R.id.activity_register_et_fname), getString(R.string.registration_success) + " " +  getString(R.string.email_sent) + email.getText().toString(), "#FFFFFF");
                        }
                    }
                });
    }
    public void cancel(View view) {
        Utils.goToLoginActivity(view);
    }
}
