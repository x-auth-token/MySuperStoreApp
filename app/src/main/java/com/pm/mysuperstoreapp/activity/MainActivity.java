package com.pm.mysuperstoreapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pm.mysuperstoreapp.R;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.image);
        String url="https://firebasestorage.googleapis.com/v0/b/mysuperstoreapp.appspot.com/o/pineapple.jpg?alt=media&token=d2e1ecec-0583-432a-88f5-f1a8aec0f238";

        Glide.with(getApplicationContext()).load(url).into(imageView);
    }

}
