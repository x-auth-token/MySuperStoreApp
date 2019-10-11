package com.pm.mysuperstoreapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

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



        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            user = mAuth.getCurrentUser();
                            progressBar.setVisibility(View.VISIBLE);
                            user.sendEmailVerification() .addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        progressBar.setVisibility(View.GONE);
                                        notification.setTextColor(Color.parseColor("#FFFFFF"));
                                        //notification.setText(getString(R.string.registration_success) + " Email sent to: " + email.getText().toString());
                                        notification.setText(getString(R.string.registration_success) + " Email sent to: " + email.getText().toString());
                                    }
                                }
                            });;
                            //notification.setTextColor(Color.parseColor("#FFFFFF"));
                            //notification.setText(getString(R.string.registration_success));

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

    public void cancelRegistration(View view) {
        final Intent intent = new Intent(getApplicationContext(),
                LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
        finish();

    }

    public void sendEmailVerificationWithContinueUrl() {
        // [START send_email_verification_with_continue_url]


        String url = "http://www.example.com/verify?uid=" + user.getUid();
        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                .setUrl(url)
                .setAndroidPackageName("com.pm.mysuperstoreapp", false, null)
                .build();

        user.sendEmailVerification(actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "Email sent.");
                        }
                    }
                });

        // [END send_email_verification_with_continue_url]
        // [START localize_verification_email]
        //auth.setLanguageCode("fr");
        // To apply the default app language instead of explicitly setting it.
        // auth.useAppLanguage();
        // [END localize_verification_email]
    }
    public void saveUserToDB(FirebaseUser user) {

    }
}
