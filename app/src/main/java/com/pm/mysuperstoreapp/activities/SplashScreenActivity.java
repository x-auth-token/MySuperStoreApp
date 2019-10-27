/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.pm.mysuperstoreapp.R;

// Splash Screen to Show on Startup
public class SplashScreenActivity extends AppCompatActivity {

    Animation anim;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);



        imageView=(ImageView)findViewById(R.id.imageView2); // Declare an imageView to show the animation.
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce_inter); // Create the animation.
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                overridePendingTransition(R.anim.fade_in, R.anim.zoom_out);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.zoom_out);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        imageView.startAnimation(anim);

    }
}
