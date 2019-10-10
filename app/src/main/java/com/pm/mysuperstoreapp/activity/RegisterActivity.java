package com.pm.mysuperstoreapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pm.mysuperstoreapp.R;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private String firstname;
    private String lastname;
    private TextView notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.activity_register_et_email);
        password = findViewById(R.id.activity_login_et_password);
        notification = findViewById(R.id.activity_register_tv_notification);
    }

    public void registerNewUser(View view) {

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "REG_OK", Toast.LENGTH_SHORT).show();
                           /* final Intent intent = new Intent(getApplicationContext(),
                                    MainActivity.class);

                            startActivity(intent);
                            finish();*/

                        } else {
                            // If sign in fails, display a message to the user.
                           // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    //Toast.LENGTH_SHORT).show();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //notification.setText(task.getException().toString());
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

    public void saveUserToDB(FirebaseUser user) {

    }
}
